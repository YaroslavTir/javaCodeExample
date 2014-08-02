package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.PasswordRestoreServiceWrapper;
import ru.fls.privateoffice.service.ServiceWrapper;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.BasePasswordRestoreDTO;
import ru.fls.privateoffice.util.dto.ExpressPasswordRestoreDTO;
import ru.fls.privateoffice.util.dto.PasswordRestoreDTO;
import ru.rzd.loyalty.api.app.AccessRestoreFault;

/**
 * Created by IntelliJ IDEA.
 * User: NKrivko
 * Date: 12.04.12
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PasswordRestorePageBuilder extends ServiceWrapper {
    @Autowired
    PasswordRestoreServiceWrapper passwordRestoreServiceWrapper;
    private final static Logger log = Logger.getLogger(PasswordRestorePageBuilder.class);

    public BasePasswordRestoreDTO buildDTOFirstStep(String login) {
        return buildDTOStep(login, null, null, null, false);
    }

    public BasePasswordRestoreDTO buildDTOSecondStep(String login, String securityQuestion, String securityAnswer) {
        return buildDTOStep(login, securityQuestion, securityAnswer, null, false);
    }

    public BasePasswordRestoreDTO buildExpressDTOSecondStep(String login, String phoneNumber) {
        return buildDTOStep(login, null, null, phoneNumber, true);
    }

    private BasePasswordRestoreDTO buildDTOStep(String login, String securityQuestion, String securityAnswer, String phoneNumber, boolean express) {
        BasePasswordRestoreDTO passwordRestoreDTO = express ? new ExpressPasswordRestoreDTO() : new PasswordRestoreDTO();
        String responseServiceResult = null;
        try {
            passwordRestoreServiceWrapper.setNewPassword(login, securityQuestion, securityAnswer, phoneNumber);
            passwordRestoreDTO.setResult(ProcessingStatuses.SUCCESS);
        } catch (AccessRestoreFault accessRestoreSecurityQuestionFault) {
            log.error(accessRestoreSecurityQuestionFault.getMessage());
            responseServiceResult = accessRestoreSecurityQuestionFault.getFaultInfo().getCode();
        }
        if (responseServiceResult != null) {
            try {
                passwordRestoreDTO.setResult(ProcessingStatuses.getConstant(responseServiceResult));
            } catch (Exception e) {
                passwordRestoreDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
            }
        }

        return passwordRestoreDTO;
    }
}
