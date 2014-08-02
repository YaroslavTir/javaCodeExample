package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.OperationsHistoryServiceWrapper;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.OperationsHistoryPageDTO;
import ru.rzd.loyalty.api.app.OperationsHistoryFault;
import ru.rzd.loyalty.api.app.OperationsHistoryResponse;


/**
 * User: NKarataeva
 * Date: 05.04.12
 * Time: 12:40
 */
@Component
public class OperationsHistoryAuthorizedPageBuilder extends AuthorizedPageBuilder {

    private final static Logger log = Logger.getLogger(OperationsHistoryAuthorizedPageBuilder.class);

    @Autowired
    OperationsHistoryServiceWrapper operationsHistoryServiceWrapper;

    @Autowired
    private Mapper mapper;

    public OperationsHistoryPageDTO buildOperationsHistoryPageDTO() {
        String result = null;
        OperationsHistoryPageDTO operationsHistoryPageDTO = new OperationsHistoryPageDTO();
        OperationsHistoryResponse operationsHistoryResponse = null;
        try {
            operationsHistoryResponse = operationsHistoryServiceWrapper.requestOperationsHistory(getSessionId());
            if (operationsHistoryResponse != null) {
                operationsHistoryPageDTO = mapper.map(operationsHistoryResponse.getAccountHistory(), OperationsHistoryPageDTO.class);
            }
            operationsHistoryPageDTO.setResult(ProcessingStatuses.SUCCESS);
        } catch (OperationsHistoryFault fault) {
            log.error("Operation history service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            result = fault.getFaultInfo().getCode();
        }

        try {
            if (result != null) {
                operationsHistoryPageDTO.setResult(ProcessingStatuses.getConstant(result));
            }
        } catch (Exception e) {
            operationsHistoryPageDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }

        return operationsHistoryPageDTO;
    }
}
