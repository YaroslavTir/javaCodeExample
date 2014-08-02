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

    public RichbannerEngineImp(RichbannerDaoImp richbannerDao) {
        this.richbannerDao = richbannerDao;
    }

    @Override
    public List<RichbannerDTO> richbannerList(RichbannerFilter richbannerFilter, List<RichbannerDTO> allRichbanners) {
        List<RichbannerDTO> richbannerDTOs = new ArrayList<RichbannerDTO>();
        for (RichbannerDTO richbannerDTO : allRichbanners) {
            log.info("Richbanner. Check richbanner for account: richbannerId=" + richbannerDTO.getId());
            if (richbannerFilter.accept(richbannerDTO)) {
                richbannerDTOs.add(richbannerDTO);
            }
        }
        return richbannerDTOs;
    }

}
