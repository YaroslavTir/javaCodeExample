package ru.fls.privateoffice.util.stub;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: NKrivko
 * Date: 21.03.12
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 */
@Component
public class HomePageStub {
    //Информация по текущему состоянию баланса
    private String premiumBals = "premiumBals";
    private String qualifiedBalsForCurrentYear = "qualifiedBalsForCurrentYear";
    private String qualifiedBalsForLastYear = "qualifiedBalsForLastYear";

    //Информация об уровне участия 
    private String currentLevelInProgram = "currentLevelInProgram";
    private String beginDate = "beginDate";
    private String endDate = "endDate";
    private String factDateToSetLevel = "factDateToSetLevel";
    private String balsForSubmitCurrentLevel="balsForSubmitCurrentLevel";

    //Статистическая информация
    private String accumulatedBals="accumulatedBals";
    private String firedBals="firedBals";
    private String spentedBals="spentedBals";

    public String getPremiumBals() {
        return premiumBals;
    }

    public void setPremiumBals(String premiumBals) {
        this.premiumBals = premiumBals;
    }

    public String getQualifiedBalsForCurrentYear() {
        return qualifiedBalsForCurrentYear;
    }

    public void setQualifiedBalsForCurrentYear(String qualifiedBalsForCurrentYear) {
        this.qualifiedBalsForCurrentYear = qualifiedBalsForCurrentYear;
    }

    public String getQualifiedBalsForLastYear() {
        return qualifiedBalsForLastYear;
    }

    public void setQualifiedBalsForLastYear(String qualifiedBalsForLastYear) {
        this.qualifiedBalsForLastYear = qualifiedBalsForLastYear;
    }

    public String getCurrentLevelInProgram() {
        return currentLevelInProgram;
    }

    public void setCurrentLevelInProgram(String currentLevelInProgram) {
        this.currentLevelInProgram = currentLevelInProgram;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFactDateToSetLevel() {
        return factDateToSetLevel;
    }

    public void setFactDateToSetLevel(String factDateToSetLevel) {
        this.factDateToSetLevel = factDateToSetLevel;
    }

    public String getBalsForSubmitCurrentLevel() {
        return balsForSubmitCurrentLevel;
    }

    public void setBalsForSubmitCurrentLevel(String balsForSubmitCurrentLevel) {
        this.balsForSubmitCurrentLevel = balsForSubmitCurrentLevel;
    }

    public String getAccumulatedBals() {
        return accumulatedBals;
    }

    public void setAccumulatedBals(String accumulatedBals) {
        this.accumulatedBals = accumulatedBals;
    }

    public String getFiredBals() {
        return firedBals;
    }

    public void setFiredBals(String firedBals) {
        this.firedBals = firedBals;
    }

    public String getSpentedBals() {
        return spentedBals;
    }

    public void setSpentedBals(String spentedBals) {
        this.spentedBals = spentedBals;
    }
}
