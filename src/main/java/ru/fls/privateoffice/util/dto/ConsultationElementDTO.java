package ru.fls.privateoffice.util.dto;

import java.util.*;

/**
 * User: NKarataeva
 * Date: 11.07.12
 * Time: 20:05
 */
public class ConsultationElementDTO {
    private Long id;
    private Integer number;
    private String category;
    private Boolean emailNotification;

    private List<ConsultationMessageDTO> messages;

    public class ConsultationMessageComparator implements Comparator<ConsultationMessageDTO> {
        @Override
        public int compare(ConsultationMessageDTO message1, ConsultationMessageDTO message2) {
            Date date1 = message1.getSendDate();
            Date date2 = message2.getSendDate();

            if (date1.after(date2))
                return 1;
            else if (date1.before(date2))
                return -1;
            else
                return 0;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(Boolean emailNotification) {
        this.emailNotification = emailNotification;
    }

    public List<ConsultationMessageDTO> getMessages() {
        if (messages == null) {
            messages = new ArrayList<ConsultationMessageDTO>();
        }

        Collections.sort(messages, new ConsultationMessageComparator());
        return messages;
    }

    public void setMessages(List<ConsultationMessageDTO> messages) {
        this.messages = messages;
    }
}
