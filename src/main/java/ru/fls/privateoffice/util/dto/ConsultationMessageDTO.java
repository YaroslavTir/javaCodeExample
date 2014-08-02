package ru.fls.privateoffice.util.dto;

import ru.fls.privateoffice.util.Replacer;

import java.util.Date;

/**
 * User: NKarataeva
 * Date: 12.07.12
 * Time: 11:11
 */
public class ConsultationMessageDTO implements Replacer<String>{
    private Long id;
    private String text;
    private Date sendDate;
    private Boolean imageEnabled;
    private Boolean imageRemoved;
    private Boolean clientNotified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text =this.replace(text);
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Boolean getImageEnabled() {
        return imageEnabled;
    }

    public void setImageEnabled(Boolean imageEnabled) {
        this.imageEnabled = imageEnabled;
    }

    public Boolean getImageRemoved() {
        return imageRemoved;
    }

    public void setImageRemoved(Boolean imageRemoved) {
        this.imageRemoved = imageRemoved;
    }

    public Boolean getClientNotified() {
        return clientNotified;
    }

    public void setClientNotified(Boolean clientNotified) {
        this.clientNotified = clientNotified;
    }

    public String replace(String text) {
        return text.replaceAll("script","");
    }
}
