package ru.fls.privateoffice.util.dto;

import org.dozer.Mapping;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: NKrivko
 * Date: 03.04.12
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public class ProfilePageDTO extends AbstractPageDTO {

    private String accountNumber;
    private String bonus;
    private String qualification;
    private Date createDate;

    @Mapping(value = "profile")
    private ProfileElementDTO profile = new ProfileElementDTO();

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ProfileElementDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileElementDTO profile) {
        this.profile = profile;
    }
}
