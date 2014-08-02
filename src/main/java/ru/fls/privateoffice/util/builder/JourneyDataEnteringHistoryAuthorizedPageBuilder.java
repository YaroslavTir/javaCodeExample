package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.JourneyDataEnteringHistoryWrapper;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.JourneyDataEnteringPageDTO;
import ru.rzd.loyalty.api.app.JourneyDataEnteringHistoryFault;
import ru.rzd.loyalty.api.app.JourneyDataEnteringHistoryResponse;


/**
 * User: NKarataeva
 * Date: 17.04.12
 * Time: 10:07
 */
@Component
public class JourneyDataEnteringHistoryAuthorizedPageBuilder extends AuthorizedPageBuilder {
    private final static Logger log = Logger.getLogger(JourneyDataEnteringHistoryAuthorizedPageBuilder.class);

    @Autowired
    private JourneyDataEnteringHistoryWrapper journeyDataEnteringHistoryWrapper;

    @Autowired
    private Mapper mapper;

    public JourneyDataEnteringPageDTO buildJourneyHistoryPageDTO() {
        String result = null;
        JourneyDataEnteringPageDTO journeyDataEnteringPageDTO = new JourneyDataEnteringPageDTO();

        JourneyDataEnteringHistoryResponse response = null;
        try {
            response = journeyDataEnteringHistoryWrapper.requestJourneyHistory(getSessionId());
            if (response != null && response.getJourneyDataEnteringHistory() != null) {
                journeyDataEnteringPageDTO = mapper.map(response.getJourneyDataEnteringHistory(), JourneyDataEnteringPageDTO.class);
            }
            journeyDataEnteringPageDTO.setResult(ProcessingStatuses.SUCCESS);
        } catch (JourneyDataEnteringHistoryFault fault) {
            log.error("Journey history service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            result = fault.getFaultInfo().getCode();
        }

        try {
            if (result != null) {
                journeyDataEnteringPageDTO.setResult(ProcessingStatuses.getConstant(result));
            }
        } catch (Exception e) {
            journeyDataEnteringPageDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }

        return journeyDataEnteringPageDTO;
    }
}
