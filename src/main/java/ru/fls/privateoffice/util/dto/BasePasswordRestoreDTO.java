package ru.fls.privateoffice.util.dto;

/**
 * User: NFadin
 * Date: 19.12.13
 * Time: 18:39
 */
public class BasePasswordRestoreDTO extends AbstractPageDTO {

    private String login;
    private String newPassword;

    public BasePasswordRestoreDTO() {
    }

    public BasePasswordRestoreDTO(String login, String newPassword) {
        this.login = login;
        this.newPassword = newPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
