package ru.fls.privateoffice.util.service.richbanner;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fls.privateoffice.dao.impl.RichbannerDaoImp;
import ru.fls.privateoffice.entity.Richbanner;
import ru.fls.privateoffice.entity.RichbannerAccount;
import ru.fls.privateoffice.entity.RichbannerExposure;
import ru.fls.privateoffice.util.dto.AccountBalancePageDTO;
import ru.fls.privateoffice.util.dto.ProfilePageDTO;
import ru.fls.privateoffice.util.dto.banner.RichbannerDTO;
import ru.rzd.loyalty.shared.json.JsonTransformer;

import java.io.IOException;
import java.util.*;

/**
 * @author YMolodkov
 * @since 16.0
 */
public class RichbannerEngineImp implements RichbannerEngine {

    private final static Logger log = Logger.getLogger(RichbannerServiceImp.class);
    @Autowired
    private RichbannerDaoImp richbannerDao;
    @Autowired
    private RichbannerFilterChecker richbannerFilterChecker;
    @Autowired
    private RichbannerService richbannerService;

    public RichbannerEngineImp(  RichbannerDaoImp richbannerDao, RichbannerFilterChecker richbannerFilterChecker, RichbannerService richbannerService) {
        this.richbannerDao = richbannerDao;
        this.richbannerFilterChecker = richbannerFilterChecker;
        this.richbannerService = richbannerService;
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

    private  List<RichbannerDTO> richbannerList(RichbannerFilter richbannerFilter) {
        List<Richbanner> allRichbanners = richbannerDao.getAllRichbanners();
        List<RichbannerDTO> richbannerDTOs = new ArrayList<RichbannerDTO>();
        for (Richbanner richbanner : allRichbanners) {
            RichbannerDTO richbannerDTO = null;
            try {
                richbannerDTO = richbannerService.toRichbannerDTO(richbanner);
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

}
