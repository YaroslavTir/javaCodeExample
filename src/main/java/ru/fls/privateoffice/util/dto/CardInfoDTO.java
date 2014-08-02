package ru.fls.privateoffice.util.dto;

import java.util.Date;

/**
 * User: ABolotov
 * Date: 22.01.14
 * Time: 15:48
 */
public class CardInfoDTO {
    private long id;
    private Date created;
    private String level;
    private Date startDate;
    private Date endDate;
    private String status;
    private int printedTimes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrintedTimes() {
        return printedTimes;
    }

    public void setPrintedTimes(int printedTimes) {
        this.printedTimes = printedTimes;
    }
}
