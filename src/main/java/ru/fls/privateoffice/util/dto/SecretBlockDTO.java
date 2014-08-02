package ru.fls.privateoffice.util.dto;

import org.springframework.stereotype.Component;

/**
 * User: NKarataeva
 * Date: 10.04.12
 * Time: 15:38
 */
@Component
public class SecretBlockDTO extends AbstractPageDTO{
    private String password;
    private String newPassword;
    private String repeatPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
