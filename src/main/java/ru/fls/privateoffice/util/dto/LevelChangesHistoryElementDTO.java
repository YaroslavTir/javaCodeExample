package ru.fls.privateoffice.util.dto;

import java.util.Date;

/**
 * User: NKarataeva
 * Date: 05.04.12
 * Time: 13:06
 */
public class LevelChangesHistoryElementDTO {

    private Date operationDate;
    private String operation;
    private String level;
    private Date startDate;
    private Date endDate;

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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
}
