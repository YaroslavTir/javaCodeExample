package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.RegistrationApplicantServiceWrapper;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.ProfileElementDTO;
import ru.fls.privateoffice.util.dto.RegistrationPageDTO;
import ru.rzd.loyalty.api.app.Profile;
import ru.rzd.loyalty.api.app.RegisterFault;
import ru.rzd.loyalty.api.app.RegistrationApplicantResponse;

/**
 * User: NKarataeva
 * Date: 09.04.12
 * Time: 11:42
 */
@Component
public class RegistrationPageBuilder {
    private final static Logger log = Logger.getLogger(RegistrationPageBuilder.class);
    @Autowired
    private RegistrationApplicantServiceWrapper registrationServiceWrapper;

    @Autowired
    private Mapper mapper;

    public RegistrationPageDTO register(ProfileElementDTO profilePageDTO) {
        // step 1
        String result = null;
        RegistrationPageDTO registrationPageDTO = new RegistrationPageDTO();
        RegistrationApplicantResponse registrationResponse = null;
        try {
            Profile profile = mapper.map(profilePageDTO, Profile.class);
            registrationResponse = registrationServiceWrapper.registerApplicant(profile);
            if (registrationResponse != null) {
                registrationPageDTO = mapper.map(registrationResponse, RegistrationPageDTO.class);
            }
            registrationPageDTO.setResult(ProcessingStatuses.SUCCESS);
        } catch (RegisterFault fault) {
            log.error("Registration applicant service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            result = fault.getFaultInfo().getCode();
        }

        try {
            if (result != null) {
                registrationPageDTO.setResult(ProcessingStatuses.getConstant(result));
            }
        } catch (Exception e) {
            registrationPageDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }

        return registrationPageDTO;
    }
}
