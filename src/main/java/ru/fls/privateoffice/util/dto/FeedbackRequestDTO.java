package ru.fls.privateoffice.util.dto;

/**
 * User: NKarataeva
 * Date: 01.09.12
 * Time: 18:45
 */
public class FeedbackRequestDTO extends AbstractPageDTO {
    private String fio;
    private String phoneNumber;
    private String email;
    private Boolean emailNotification;
    private String message;
    private String category;
    private Long consultationId;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(Boolean emailNotification) {
        this.emailNotification = emailNotification;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberString() {
        if (this.phoneNumber == null) {
            return null;
        }
        return this.phoneNumber.substring(1);
    }

    public void setPhoneNumberString(String phone) {
        if (phone == null) {
            this.phoneNumber = null;
            return;
        }
        String num = phone.replaceAll("[^0-9]", "");
        if (num.length() == 10) {
            this.phoneNumber = "8" + num;
        } else {
            this.phoneNumber = null;
        }
    }

}
