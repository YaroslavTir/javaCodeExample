package ru.fls.privateoffice.util.service.richbanner;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.fls.privateoffice.dao.impl.RichbannerDaoImp;
import ru.fls.privateoffice.entity.*;
import ru.fls.privateoffice.util.dto.AccountBalancePageDTO;
import ru.fls.privateoffice.util.dto.ProfilePageDTO;
import ru.fls.privateoffice.util.dto.RichbannerDTO;
import ru.rzd.loyalty.shared.json.JsonTransformer;

import javax.persistence.PersistenceException;
import java.io.*;
import java.util.*;

/**
 * @author YMolodkov
 * @since 14.0
 */
@Service
@Transactional
public class RichbannerServiceImp implements RichbannerService {
    private final static Logger log = Logger.getLogger(RichbannerServiceImp.class);
    private final static int MAX_BATCH_SIZE = 5000;
    private final static int ACCOUNT_NUMBER_LENGTH = 13;

    @Autowired
    private Mapper mapper;
    private String charSet = "windows-1251";
    @Autowired
    protected JsonTransformer jt;
    @Autowired
    private RichbannerDaoImp richbannerDao;
    @Autowired
    private RichbannerFilterChecker richbannerFilterChecker;

    public RichbannerServiceImp() {
    }

    public RichbannerServiceImp(Mapper mapper, JsonTransformer jt, RichbannerDaoImp richbannerDao, RichbannerFilterChecker richbannerFilterChecker) {
        this.mapper = mapper;
        this.jt = jt;
        this.richbannerDao = richbannerDao;
        this.richbannerFilterChecker = richbannerFilterChecker;
    }


    @Override
    public void delete(Long id) throws PersistenceException {
        log.info("Richbanner. Delete richbanner: id = " + id);
        richbannerDao.cleanRichbannerAccountsByRichbanner(id);
        richbannerDao.cleanRichbannerExposureByRichbanner(id);
        richbannerDao.deleteObject(Richbanner.class, id);
    }


    @Override
    public Richbanner getRichbanner(Long id) {
        return richbannerDao.getObject(Richbanner.class, id);
    }

    @Override
    public RichbannerDTO getRichbannerDTO(Long id) {
        Richbanner richbanner = richbannerDao.getObject(Richbanner.class, id);
        try {
            return toRichbannerDTO(richbanner);
        } catch (IOException e) {
            log.error("Richbanner. Cannot convert json", e);
            return null;
        }
    }

    @Override
    public RichbannerDTO toRichbannerDTO(Richbanner richbanner) throws IOException {
        RichbannerDTO richbannerDTO = mapper.map(richbanner, RichbannerDTO.class);
        richbannerDTO.setFilter(jt.convertToObject(richbanner.getFilterData(), RichbannerClientFilter.class));
        return richbannerDTO;
    }

    @Override
    public Richbanner toRichbanner(RichbannerDTO richbannerDTO) {
        Richbanner richbanner = mapper.map(richbannerDTO, Richbanner.class);
        RichbannerClientFilter filter = richbannerDTO.getFilter() != null ? richbannerDTO.getFilter() : new RichbannerClientFilter();
        richbanner.setFilterData(jt.transform(filter));
        return richbanner;
    }

    @Override
    public void saveRichbanner(RichbannerDTO richbannerDTO) {
        log.info("Richbanner. save richbanner: " + richbannerDTO.toString());
        Richbanner richbanner = toRichbanner(richbannerDTO);
        richbannerDao.save(richbanner);
    }

    @Override
    public void saveRichbanner(Richbanner richbanner, MultipartFile accountFile) throws IOException {
        log.info("Richbanner. save richbanner: " + richbanner.toString());
        richbannerDao.save(richbanner);
        if (accountFile.getSize() > 0) {
            richbanner.setAccountFileName(accountFile.getOriginalFilename());
            saveRichbannerAccounts(accountFile.getInputStream(), richbanner);
            richbannerDao.save(richbanner);
        }
    }


    @Override
    public List<RichbannerDTO> richbannerList(RichbannerFilter richbannerFilter) {
        List<Richbanner> allRichbanners = richbannerDao.getAllRichbanners();
        List<RichbannerDTO> richbannerDTOs = new ArrayList<RichbannerDTO>();
        for (Richbanner richbanner : allRichbanners) {
            RichbannerDTO richbannerDTO = null;
            try {
                richbannerDTO = toRichbannerDTO(richbanner);
                log.info("Richbanner. Check richbanner for account: richbannerId=" + richbannerDTO.getId());
                if (richbannerFilter.accept(richbannerDTO)){
                    richbannerDTOs.add(richbannerDTO);
                }
            } catch (IOException e) {
                log.error("Richbanner. Cannot convert json", e);
            }
        }
        return richbannerDTOs;
    }

    @Override
    public List<RichbannerDTO> getAllRichbanners() {
        return richbannerList(new RichbannerAllFilter());
    }

    @Override
    public Set<RichbannerViewDTO> getRichBannerViewSet(ProfilePageDTO page, AccountBalancePageDTO balance) {
        String accountNumber = page.getAccountNumber();
        log.info("Richbanner. Get all banners for: " + accountNumber);
        List<RichbannerAccount> bannerBasesByAccount = richbannerDao.getBannerBasesByAccount(accountNumber);
        Set<Long> richbanners = new HashSet<Long>();
        for (RichbannerAccount richbannerAccount : bannerBasesByAccount) {
            richbanners.add(richbannerAccount.getBannerBase().getId());
        }

        List<RichbannerExposure> exposureByAccount = richbannerDao.getExposureByAccount(accountNumber);
        Map<Long, Integer> exposures = new HashMap<Long, Integer>();
        for (RichbannerExposure richbannerExposure : exposureByAccount) {
            exposures.put(richbannerExposure.getBannerBase().getId(), richbannerExposure.getExposures());
        }
        Set<String> existsRichbannerAccount = new HashSet<String>(richbannerDao.getExistsRichbannerAccount());
        List<RichbannerDTO> richbannerDTOs = richbannerList(new RichbannerByProfileFilter(page, balance, richbanners, exposures, richbannerFilterChecker, existsRichbannerAccount));
        final Set<RichbannerViewDTO> result = new TreeSet<RichbannerViewDTO>();
        for (RichbannerDTO richbannerDTO : richbannerDTOs) {
            result.add(RichbannerViewDTO.create(richbannerDTO));
        }
        return result;
    }

    @Override
    public void incAccountExposure(Long bannerId, ProfilePageDTO page) {
        log.info("Richbanner. Increment account exposure : id =" + bannerId);
        Richbanner richbanner = richbannerDao.getObject(Richbanner.class, bannerId);
        if (richbanner != null && (Boolean.TRUE.equals(richbanner.getFirstVisit()) || richbanner.getExposures() != null)) {
            richbannerDao.incAccountExposure(bannerId, page.getAccountNumber());
        }
    }

    @Override
    public void saveRichbannerAccounts(InputStream inputStream, BannerBase bannerBase) throws IOException {
        long startMs = System.currentTimeMillis();
        BufferedReader reader;
        richbannerDao.cleanRichbannerAccountsByRichbanner(bannerBase.getId());
        reader = new BufferedReader(new InputStreamReader(inputStream, charSet));
        Set<RichbannerAccount> richbannerAccountSet = new HashSet<RichbannerAccount>();
        String accountNumber;
        try {
            while ((accountNumber = reader.readLine()) != null) {
                String accountNumberPrep = removeAllNotNumberChr(accountNumber);
                if (!isAccountNumber(accountNumberPrep)) continue;
                richbannerAccountSet.add(new RichbannerAccount(accountNumberPrep, bannerBase));
                if (richbannerAccountSet.size() >= MAX_BATCH_SIZE) {
                    richbannerDao.saveRichbannerAccounts(richbannerAccountSet);
                    richbannerAccountSet.clear();
                }
            }
        } finally {
            reader.close();
        }
        richbannerDao.saveRichbannerAccounts(richbannerAccountSet);
        richbannerAccountSet.clear();
        log.info(String.format("Richbanner. Richbanner accounts saved in %s s", (System.currentTimeMillis() - startMs) / 1000));
    }

    public boolean isAccountNumber(String accountNumber) {
        return StringUtils.isNotBlank(accountNumber) &&
                accountNumber.length() == ACCOUNT_NUMBER_LENGTH &&
                isNumeric(accountNumber);
    }

    public String removeAllNotNumberChr(String str) {
        return str.replaceAll("[^0-9]", "");
    }

    private boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

}
