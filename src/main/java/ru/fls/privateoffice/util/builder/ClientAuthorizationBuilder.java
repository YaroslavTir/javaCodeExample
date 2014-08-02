package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.ClientAuthorizationServiceWrapper;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.ClientAuthorizationDTO;
import ru.rzd.loyalty.api.security.AuthorizationFault;
import ru.rzd.loyalty.api.security.ClientAuthorizationResponse;

/**
 * User: NKarataeva
 * Date: 10.04.12
 * Time: 12:27
 */
@Component
public class ClientAuthorizationBuilder {
    private final static Logger log = Logger.getLogger(ClientAuthorizationBuilder.class);
    @Autowired
    private ClientAuthorizationServiceWrapper clientAuthorizationServiceWrapper;

    @Autowired
    private Mapper mapper;

    public ClientAuthorizationDTO buildClientAuthorizationPageDTO(String accountNumber, String password) {
        String result = null;
        ClientAuthorizationDTO clientAuthorizationPageDTO = new ClientAuthorizationDTO();
        ClientAuthorizationResponse clientAuthorizationResponse = null;
        try {
            clientAuthorizationResponse = clientAuthorizationServiceWrapper.authorize(accountNumber, password);
            if (clientAuthorizationResponse != null) {
                clientAuthorizationPageDTO = mapper.map(clientAuthorizationResponse, ClientAuthorizationDTO.class);
            }
            clientAuthorizationPageDTO.setResult(ProcessingStatuses.SUCCESS);
        } catch (AuthorizationFault fault) {
            log.error("Client authorization service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            result = fault.getFaultInfo().getCode();
        }

        try {
            if (result != null) {
                clientAuthorizationPageDTO.setResult(ProcessingStatuses.getConstant(result));
            }
        } catch (Exception e) {
            clientAuthorizationPageDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }

        return clientAuthorizationPageDTO;
    }
}
