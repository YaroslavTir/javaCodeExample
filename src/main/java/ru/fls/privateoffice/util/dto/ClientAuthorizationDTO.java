package ru.fls.privateoffice.util.dto;

/**
 * User: NKarataeva
 * Date: 10.04.12
 * Time: 12:29
 */
public class ClientAuthorizationDTO extends AbstractPageDTO {
    private String clientId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
