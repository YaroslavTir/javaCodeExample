package ru.fls.privateoffice.util.dto;

/**
 * Created by IntelliJ IDEA.
 * User: NKrivko
 * Date: 12.04.12
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public class PasswordRestoreDTO extends BasePasswordRestoreDTO {

    private String securityQuestion;
    private String securityAnswer;

    public PasswordRestoreDTO() {
    }

    public PasswordRestoreDTO(BasePasswordRestoreDTO basePasswordRestoreDTO) {
        super(basePasswordRestoreDTO.getLogin(), basePasswordRestoreDTO.getNewPassword());
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

}
