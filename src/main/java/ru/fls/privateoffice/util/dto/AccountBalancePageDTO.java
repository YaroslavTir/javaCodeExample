package ru.fls.privateoffice.util.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: NKarataeva
 * Date: 05.04.12
 * Time: 9:40
 */
public class AccountBalancePageDTO extends AbstractPageDTO {
    private BigDecimal bonus;
    private BigDecimal bonusThisYear;
    private BigDecimal bonusLastYear;
    private String currentLevel;
    private Date startDate;
    private Date endDate;
    private Date operationDate;
    private BigDecimal qualificationConfirmation;
    private BigDecimal incomingBonus;
    private BigDecimal burnedBonus;
    private BigDecimal spendBonus;
    protected BigDecimal nextLevel;

    public BigDecimal getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(BigDecimal nextLevel) {
        this.nextLevel = nextLevel;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getBonusThisYear() {
        return bonusThisYear;
    }

    public void setBonusThisYear(BigDecimal bonusThisYear) {
        this.bonusThisYear = bonusThisYear;
    }

    public BigDecimal getBonusLastYear() {
        return bonusLastYear;
    }

    public void setBonusLastYear(BigDecimal bonusLastYear) {
        this.bonusLastYear = bonusLastYear;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
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

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public BigDecimal getQualificationConfirmation() {
        return qualificationConfirmation;
    }

    public void setQualificationConfirmation(BigDecimal qualificationConfirmation) {
        this.qualificationConfirmation = qualificationConfirmation;
    }

    public BigDecimal getIncomingBonus() {
        return incomingBonus;
    }

    public void setIncomingBonus(BigDecimal incomingBonus) {
        this.incomingBonus = incomingBonus;
    }

    public BigDecimal getBurnedBonus() {
        return burnedBonus;
    }

    public void setBurnedBonus(BigDecimal burnedBonus) {
        this.burnedBonus = burnedBonus;
    }

    public BigDecimal getSpendBonus() {
        return spendBonus;
    }

    public void setSpendBonus(BigDecimal spendBonus) {
        this.spendBonus = spendBonus;
    }

}
