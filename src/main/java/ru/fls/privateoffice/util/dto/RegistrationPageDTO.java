package ru.fls.privateoffice.util.dto;

/**
 * User: NKarataeva
 * Date: 09.04.12
 * Time: 11:44
 */
public class RegistrationPageDTO extends AbstractPageDTO {
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
