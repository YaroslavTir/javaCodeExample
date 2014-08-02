package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.EditProfileInformationServiceWrapper;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.BaseDTO;
import ru.fls.privateoffice.util.dto.ProfileElementDTO;
import ru.rzd.loyalty.api.app.EditProfile;
import ru.rzd.loyalty.api.app.EditProfileFault;
import ru.rzd.loyalty.api.app.EditProfileResponse;


/**
 * Created by IntelliJ IDEA.
 * User: NKrivko
 * Date: 05.04.12
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
@Component
public class EditProfileInformationAuthorizedPageBuilder extends AuthorizedPageBuilder {
    @Autowired
    private EditProfileInformationServiceWrapper editProfileInformationServiceWrapper;
    private final static Logger log = Logger.getLogger(EditProfileInformationAuthorizedPageBuilder.class);
    @Autowired
    Mapper mapper;

    public BaseDTO buildResult(ProfileElementDTO newProfile) {
        EditProfileResponse response = null;
        String responseServiceResult = null;
        BaseDTO pageDTO = new BaseDTO();
        try {
            EditProfile newRzdProfile = convertToProfile(newProfile);
            response = editProfileInformationServiceWrapper.requestEditProfile(getSessionId(), newRzdProfile);
            pageDTO.setResult(ProcessingStatuses.SUCCESS);
        } catch (EditProfileFault editProfileFault) {
            log.error("EditProfile service error: code = " + editProfileFault.getFaultInfo().getCode() + " description = " + editProfileFault.getFaultInfo().getMessage());
            responseServiceResult = editProfileFault.getFaultInfo().getCode();
        }
        try {
            if (responseServiceResult != null) {
                pageDTO.setResult(ProcessingStatuses.getConstant(responseServiceResult));
            }
        } catch (Exception e) {
           pageDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }

        return pageDTO;
    }

    public EditProfile convertToProfile(ProfileElementDTO dto) {
        EditProfile profile = mapper.map(dto, EditProfile.class);
        return profile;
    }
}
