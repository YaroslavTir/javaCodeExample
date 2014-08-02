package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.JourneyDataEnteringWrapper;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.JourneyDataEnteringElementDTO;
import ru.fls.privateoffice.util.dto.JourneyDataEnteringResultDTO;
import ru.rzd.loyalty.api.app.JourneyDataEnteringEvent;
import ru.rzd.loyalty.api.app.JourneyDataEnteringFault;
import ru.rzd.loyalty.api.app.JourneyDataEnteringGeneralInfo;
import ru.rzd.loyalty.api.app.JourneyDataEnteringResponse;


/**
 * User: NKarataeva
 * Date: 16.04.12
 * Time: 15:26
 */
@Component
public class JourneyDataEnteringAuthorizedPageBuilder extends AuthorizedPageBuilder {
    private final static Logger log = Logger.getLogger(JourneyDataEnteringAuthorizedPageBuilder.class);
    @Autowired
    private JourneyDataEnteringWrapper journeyDataEnteringWrapper;

    @Autowired
    private Mapper mapper;

    public JourneyDataEnteringResultDTO buildJourneyDataEnteringPageDTO(JourneyDataEnteringElementDTO request) {
        String result = null;
        JourneyDataEnteringResultDTO resultDTO = new JourneyDataEnteringResultDTO();
        JourneyDataEnteringResponse journeyDataEnteringResponse = null;
        try {
            journeyDataEnteringResponse = journeyDataEnteringWrapper.requestJourneyDataEntering(getSessionId(), convertToRequest(request));
            if (journeyDataEnteringResponse != null) {
                resultDTO = mapper.map(journeyDataEnteringResponse, JourneyDataEnteringResultDTO.class);
            }
            resultDTO.setResult(ProcessingStatuses.SUCCESS);
        } catch (JourneyDataEnteringFault fault) {
            log.error("Journey data entering service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            result = fault.getFaultInfo().getCode();
        }

        try {
            if (result != null) {
                resultDTO.setResult(ProcessingStatuses.getConstant(result));
            }
        } catch (Exception e) {
            resultDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }

        return resultDTO;
    }

    private JourneyDataEnteringGeneralInfo convertToRequest(JourneyDataEnteringElementDTO dto) {
        JourneyDataEnteringEvent obj = mapper.map(dto, JourneyDataEnteringEvent.class);
        return obj.getJourneyDataEnteringGeneralInfo();
    }

}
