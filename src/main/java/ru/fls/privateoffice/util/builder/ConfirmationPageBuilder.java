package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.RegistrationConfirmationServiceWrapper;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.ConfirmationPageDTO;
import ru.rzd.loyalty.api.app.RegistrationConfirmationFault;
import ru.rzd.loyalty.api.app.RegistrationConfirmationResponse;

/**
 * User: NKarataeva
 * Date: 09.04.12
 * Time: 11:42
 */
@Component
public class ConfirmationPageBuilder {
    private final static Logger log = Logger.getLogger(ConfirmationPageBuilder.class);
    @Autowired
    private RegistrationConfirmationServiceWrapper registrationConfirmationServiceWrapper;

    @Autowired
    private Mapper mapper;

    public ConfirmationPageDTO confirm(String accountNumber) {
        String result = null;
        ConfirmationPageDTO confirmationPageDTO = new ConfirmationPageDTO();
        RegistrationConfirmationResponse registrationConfirmationResponse = null;
        try {
            registrationConfirmationResponse =  registrationConfirmationServiceWrapper.confirmRegistration(accountNumber);
            if (registrationConfirmationResponse != null) {
                confirmationPageDTO = mapper.map(registrationConfirmationResponse, ConfirmationPageDTO.class);
            }
            confirmationPageDTO.setResult(ProcessingStatuses.SUCCESS);
        } catch (RegistrationConfirmationFault fault) {
            log.error("Confirmation service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            result = fault.getFaultInfo().getCode();
        }

        try {
            if (result != null) {
                confirmationPageDTO.setResult(ProcessingStatuses.getConstant(result));
            }
        } catch (Exception e) {
            confirmationPageDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }

        return confirmationPageDTO;
    }
}
