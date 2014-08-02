package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.LevelChangesHistoryServiceWrapper;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.LevelChangesHistoryPageDTO;
import ru.rzd.loyalty.api.app.ParticipationLevelChangesHistoryFault;
import ru.rzd.loyalty.api.app.ParticipationLevelChangesHistoryResponse;

/**
 * User: NKarataeva
 * Date: 05.04.12
 * Time: 13:04
 */
@Component
public class LevelChangesHistoryAuthorizedPageBuilder extends AuthorizedPageBuilder {
    private final static Logger log = Logger.getLogger(LevelChangesHistoryAuthorizedPageBuilder.class);

    @Autowired
    LevelChangesHistoryServiceWrapper levelChangesHistoryServiceWrapper;

    @Autowired
    private Mapper mapper;

    public LevelChangesHistoryPageDTO buildLevelChangesHistoryPageDTO() {
        String result = null;
        LevelChangesHistoryPageDTO levelChangesHistoryPageDTO = new LevelChangesHistoryPageDTO();
        ParticipationLevelChangesHistoryResponse participationLevelChangesHistoryResponse = null;
        try {
            participationLevelChangesHistoryResponse = levelChangesHistoryServiceWrapper.requestParticipationLevelChangesHistory(getSessionId());
            if (participationLevelChangesHistoryResponse != null) {
                levelChangesHistoryPageDTO = mapper.map(participationLevelChangesHistoryResponse.getParticipationLevelChanges(), LevelChangesHistoryPageDTO.class);
            }
            levelChangesHistoryPageDTO.setResult(ProcessingStatuses.SUCCESS);
        } catch (ParticipationLevelChangesHistoryFault fault) {
            log.error("Participation level changes history service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            result = fault.getFaultInfo().getCode();
        }

        try {
            if (result != null) {
                levelChangesHistoryPageDTO.setResult(ProcessingStatuses.getConstant(result));
            }
        } catch (Exception e) {
            levelChangesHistoryPageDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }

        return levelChangesHistoryPageDTO;
    }
}
