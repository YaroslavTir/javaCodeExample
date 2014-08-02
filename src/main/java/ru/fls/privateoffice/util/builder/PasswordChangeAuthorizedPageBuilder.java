package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.PasswordChangeServiceWrapper;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.BaseDTO;
import ru.fls.privateoffice.util.dto.SecretBlockDTO;
import ru.rzd.loyalty.api.security.ChangePasswordFault;
import ru.rzd.loyalty.api.security.PasswordChangeRequest;
import ru.rzd.loyalty.api.security.PasswordChangeResponse;


/**
 * Created by IntelliJ IDEA.
 * User: NKrivko
 * Date: 11.04.12
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PasswordChangeAuthorizedPageBuilder extends AuthorizedPageBuilder {
    @Autowired
    PasswordChangeServiceWrapper passwordChangeServiceWrapper;
    private final static Logger log = Logger.getLogger(PasswordChangeAuthorizedPageBuilder.class);

    public BaseDTO buildSecretBlockDTO(SecretBlockDTO inputDTO) {
        BaseDTO baseDTO = new BaseDTO();
        String responseServiceResult = null;
        PasswordChangeResponse passwordChangeResponse = null;
        PasswordChangeRequest request = new PasswordChangeRequest();
        try {
            request.setNewPassword(inputDTO.getNewPassword());
            request.setOldPassword(inputDTO.getPassword());
            passwordChangeResponse = passwordChangeServiceWrapper.requestPasswordChange(getSessionId(), request);
            baseDTO.setResult(ProcessingStatuses.SUCCESS);
        } catch (ChangePasswordFault changePasswordFault) {
            log.error("PasswordChange service error: code = " + changePasswordFault.getFaultInfo().getCode() + " description = " + changePasswordFault.getFaultInfo().getMessage());
            responseServiceResult = changePasswordFault.getFaultInfo().getCode();
        }
        try {
            if (responseServiceResult != null) {
                baseDTO.setResult(ProcessingStatuses.getConstant(responseServiceResult));
            }
        } catch (Exception e) {
            baseDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }
        return baseDTO;
    }
}
