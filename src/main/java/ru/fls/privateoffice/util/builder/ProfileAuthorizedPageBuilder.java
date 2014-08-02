package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.ProfilePageServiceWrapper;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.ProfilePageDTO;
import ru.rzd.loyalty.api.app.ProfileInformationFault;
import ru.rzd.loyalty.api.app.ProfileInformationResponse;


/**
 * Created by IntelliJ IDEA.
 * User: NKrivko
 * Date: 04.04.12
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ProfileAuthorizedPageBuilder extends AuthorizedPageBuilder {
    private final static Logger log = Logger.getLogger(ProfileAuthorizedPageBuilder.class);
    @Autowired
    ProfilePageServiceWrapper profilePageServiceWrapper;
    @Autowired
    private Mapper mapper;

    public ProfilePageDTO buildProfilePage() {
        String result = null;
        ProfilePageDTO profilePageDTO = new ProfilePageDTO();
        ProfileInformationResponse profileResponse = null;
        try {
            profileResponse = profilePageServiceWrapper.requestProfileInformation(getSessionId());
            if (profileResponse != null) {
                profilePageDTO = mapper.map(profileResponse, ProfilePageDTO.class);
            }

            profilePageDTO.setResult(ProcessingStatuses.SUCCESS);
        } catch (ProfileInformationFault fault) {
            log.error("Profile service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            result = fault.getFaultInfo().getCode();
        }

        try {
            if (result != null) {
                profilePageDTO.setResult(ProcessingStatuses.getConstant(result));
            }
        } catch (Exception e) {
            profilePageDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }

        return profilePageDTO;
    }
}
