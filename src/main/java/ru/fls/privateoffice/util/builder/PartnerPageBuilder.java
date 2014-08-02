package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.PartnerServiceWrapper;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.PartnerPageDTO;
import ru.rzd.loyalty.api.app.PartnerFault;
import ru.rzd.loyalty.api.app.PartnerResponse;


/**
 * User: NKarataeva
 * Date: 18.12.12
 * Time: 14:08
 */
@Component
public class PartnerPageBuilder {
    private final static Logger log = Logger.getLogger(PartnerPageBuilder.class);
    @Autowired
    private PartnerServiceWrapper partnerServiceWrapper;

    @Autowired
    private Mapper mapper;

    public PartnerPageDTO buildPartnerPageDTO() {
        String result = null;
        PartnerPageDTO partnerPageDTO = new PartnerPageDTO();
        PartnerResponse partnerResponse = null;
        try {
            partnerResponse = partnerServiceWrapper.getPartners();
            if (partnerResponse != null) {
                partnerPageDTO = mapper.map(partnerResponse.getPartnerElements(), PartnerPageDTO.class);
            }
            partnerPageDTO.setResult(ProcessingStatuses.SUCCESS);
            log.info("Partner list completed : " + partnerPageDTO.getElements().size() + " records");
        } catch (PartnerFault fault) {
            log.error("Partner service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            result = fault.getFaultInfo().getCode();
        } catch (RuntimeException e) {
            log.error("Partner service error", e);
            return new PartnerPageDTO(ProcessingStatuses.UNKNOWN_ERROR);
        }

        try {
            if (result != null) {
                partnerPageDTO.setResult(ProcessingStatuses.getConstant(result));
            }
        } catch (Exception e) {
            partnerPageDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }

        return partnerPageDTO;
    }
}
