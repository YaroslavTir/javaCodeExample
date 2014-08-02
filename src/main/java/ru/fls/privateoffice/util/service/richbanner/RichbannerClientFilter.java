package ru.fls.privateoffice.util.service.richbanner;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import ru.fls.privateoffice.entity.types.UserGender;
import ru.fls.privateoffice.utils.DateFormatter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author YMolodkov
 * @since 14.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RichbannerClientFilter {
    protected DateFormatter dateFormatter = new DateFormatter();

    private String accountNumber = "";
    private String[] accountStatus;
    private String[] level;
    private Long balanceFrom;
    private Long balanceTo;
    private Date registrationDateFrom;
    private Date registrationDateTo;
    private Date lastActiveDateFrom;
    private Date lastActiveDateTo;

    private String firstName = "";
    private String lastName = "";
    private String middleName = "";
    private Boolean mailer;
    private String email = "";
    private Date birthDateFrom;
    private Date birthDateTo;
    private Integer birthDay;
    private Integer birthMonth;
    private Integer birthYear;
    private String birthplace = "";
    private Boolean todayIsBirthday;
    private String[] additionStatus;
    private Boolean swindler;
    private String region = "";
    private String locality = "";
    private String addressComment = "";
    private Boolean transliterationMismatch;
    private String documentNumber;

    private String[] registrationChannel;
    private String partnerId="";
    private String[] cobrandingPartnerId;

    private String[] programs;
    private String infoSources="";
    private String sex = UserGender.ALL.toString();
    private Boolean isBirthday;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String[] getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String[] accountStatus) {
        this.accountStatus = accountStatus;
    }

    @JsonIgnore
    public List<String> getAccountStatusList() {
        return accountStatus != null ? Arrays.asList(accountStatus) : Collections.EMPTY_LIST;
    }

    public String[] getLevel() {
        return level;
    }

    public void setLevel(String[] level) {
        this.level = level;
    }

    @JsonIgnore
    public List<String> getLevelList() {
        return level != null ? Arrays.asList(level) : Collections.EMPTY_LIST;
    }

    public Long getBalanceFrom() {
        return balanceFrom;
    }

    public void setBalanceFrom(Long balanceFrom) {
        this.balanceFrom = balanceFrom;
    }

    public Long getBalanceTo() {
        return balanceTo;
    }

    public void setBalanceTo(Long balanceTo) {
        this.balanceTo = balanceTo;
    }

    public Date getRegistrationDateFrom() {
        return registrationDateFrom;
    }

    public void setRegistrationDateFrom(Date registrationDateFrom) {
        this.registrationDateFrom = registrationDateFrom;
    }

    @JsonIgnore
    public String getRegistrationDateFromString() {
        return dateFormatter.getDateAsStringShort(registrationDateFrom);
    }

    @JsonIgnore
    public void setRegistrationDateFromString(String registrationDateFrom) {
        this.registrationDateFrom = dateFormatter.parseDateShort(registrationDateFrom);
    }

    public Date getRegistrationDateTo() {
        return registrationDateTo;
    }

    public void setRegistrationDateTo(Date registrationDateTo) {
        this.registrationDateTo = registrationDateTo;
    }

    @JsonIgnore
    public String getRegistrationDateToString() {
        return dateFormatter.getDateAsStringShort(registrationDateTo);
    }

    @JsonIgnore
    public void setRegistrationDateToString(String registrationDateTo) {
        this.registrationDateTo = dateFormatter.parseDateShort(registrationDateTo);
    }

    public Date getLastActiveDateFrom() {
        return lastActiveDateFrom;
    }

    public void setLastActiveDateFrom(Date lastActiveDateFrom) {
        this.lastActiveDateFrom = lastActiveDateFrom;
    }

    @JsonIgnore
    public String getLastActiveDateFromString() {
        return dateFormatter.getDateAsStringShort(lastActiveDateFrom);
    }

    @JsonIgnore
    public void setLastActiveDateFromString(String lastActiveDateFrom) {
        this.lastActiveDateFrom = dateFormatter.parseDateShort(lastActiveDateFrom);
    }

    public Date getLastActiveDateTo() {
        return lastActiveDateTo;
    }

    public void setLastActiveDateTo(Date lastActiveDateTo) {
        this.lastActiveDateTo = lastActiveDateTo;
    }

    @JsonIgnore
    public String getLastActiveDateToString() {
        return dateFormatter.getDateAsStringShort(lastActiveDateTo);
    }

    @JsonIgnore
    public void setLastActiveDateToString(String lastActiveDateTo) {
        this.lastActiveDateTo = dateFormatter.parseDateShort(lastActiveDateTo);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Boolean getMailer() {
        return mailer;
    }

    public void setMailer(Boolean mailer) {
        this.mailer = mailer;
    }

    @JsonIgnore
    public String getMailerString() {
        return String.valueOf(mailer);
    }

    @JsonIgnore
    public void setMailerString(String mailer) {
        if (StringUtils.isNotBlank(mailer) && !"null".equals(mailer)) {
            this.mailer = Boolean.valueOf(mailer);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDateFrom() {
        return birthDateFrom;
    }

    public void setBirthDateFrom(Date birthDateFrom) {
        this.birthDateFrom = birthDateFrom;
    }

    @JsonIgnore
    public String getBirthDateFromString() {
        return dateFormatter.getDateAsStringShort(birthDateFrom);
    }

    @JsonIgnore
    public void setBirthDateFromString(String birthDateFrom) {
        this.birthDateFrom = dateFormatter.parseDateShort(birthDateFrom);
    }

    public Date getBirthDateTo() {
        return birthDateTo;
    }

    public void setBirthDateTo(Date birthDateTo) {
        this.birthDateTo = birthDateTo;
    }

    @JsonIgnore
    public String getBirthDateToString() {
        return dateFormatter.getDateAsStringShort(birthDateTo);
    }

    @JsonIgnore
    public void setBirthDateToString(String birthDateTo) {
        this.birthDateTo = dateFormatter.parseDateShort(birthDateTo);
    }

    public Integer getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Integer birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }


    public Boolean getTodayIsBirthday() {
        return todayIsBirthday;
    }

    public void setTodayIsBirthday(Boolean todayIsBirthday) {
        this.todayIsBirthday = todayIsBirthday;
    }

    @JsonIgnore
    public void setTodayIsBirthdayString(String todayIsBirthday) {

        if (StringUtils.isNotBlank(todayIsBirthday) && !"null".equals(todayIsBirthday)) {
            this.todayIsBirthday = Boolean.valueOf(todayIsBirthday);
        }
    }

    public String[] getAdditionStatus() {
        return additionStatus;
    }

    public void setAdditionStatus(String[] additionStatus) {
        this.additionStatus = additionStatus;
    }

    @JsonIgnore
    public List<String> getAdditionStatusList() {
        return additionStatus != null ? Arrays.asList(additionStatus) : Collections.EMPTY_LIST;
    }

    public Boolean getSwindler() {
        return swindler;
    }

    public void setSwindler(Boolean swindler) {
        this.swindler = swindler;
    }

    @JsonIgnore
    public void setSwindlerString(String swindler) {
        if (StringUtils.isNotBlank(swindler) && !"null".equals(swindler)) {
            this.swindler = Boolean.valueOf(swindler);
        }
    }

    @JsonIgnore
    public String getSwindlerString() {
        return String.valueOf(swindler);
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddressComment() {
        return addressComment;
    }

    public void setAddressComment(String addressComment) {
        this.addressComment = addressComment;
    }

    public Boolean getTransliterationMismatch() {
        return transliterationMismatch;
    }

    public void setTransliterationMismatch(Boolean transliterationMismatch) {
        this.transliterationMismatch = transliterationMismatch;
    }

    @JsonIgnore
    public String getTransliterationMismatchString() {
        return String.valueOf(transliterationMismatch);
    }

    @JsonIgnore
    public void setTransliterationMismatchString(String transliterationMismatch) {
        if (StringUtils.isNotBlank(transliterationMismatch) && !"null".equals(transliterationMismatch)) {
            this.transliterationMismatch = Boolean.valueOf(transliterationMismatch);
        }
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String[] getRegistrationChannel() {
        return registrationChannel;
    }

    public void setRegistrationChannel(String[] registrationChannel) {
        this.registrationChannel = registrationChannel;
    }

    @JsonIgnore
    public List<String> getRegistrationChannelList() {
        return registrationChannel != null ? Arrays.asList(registrationChannel) : Collections.EMPTY_LIST;
    }


    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }


    public String[] getCobrandingPartnerId() {
        return cobrandingPartnerId;
    }

    public void setCobrandingPartnerId(String[] cobrandingPartnerId) {
        this.cobrandingPartnerId = cobrandingPartnerId;
    }

    @JsonIgnore
    public List<String> getCobrandingPartnerIdList() {
        return cobrandingPartnerId != null ? Arrays.asList(cobrandingPartnerId) : Collections.EMPTY_LIST;
    }


    public String[] getPrograms() {
        return programs;
    }

    public void setPrograms(String[] programs) {
        this.programs = programs;
    }

    @JsonIgnore
    public List<String> getProgramsList() {
        return programs != null ? Arrays.asList(programs) : Collections.EMPTY_LIST;
    }

    public String getInfoSources() {
        return infoSources;
    }

    public void setInfoSources(String infoSources) {
        this.infoSources = infoSources;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RichbannerClientFilter that = (RichbannerClientFilter) o;

        if (accountNumber != null ? !accountNumber.equals(that.accountNumber) : that.accountNumber != null)
            return false;
        if (!Arrays.equals(accountStatus, that.accountStatus)) return false;
        if (!Arrays.equals(additionStatus, that.additionStatus)) return false;
        if (addressComment != null ? !addressComment.equals(that.addressComment) : that.addressComment != null)
            return false;
        if (balanceFrom != null ? !balanceFrom.equals(that.balanceFrom) : that.balanceFrom != null) return false;
        if (balanceTo != null ? !balanceTo.equals(that.balanceTo) : that.balanceTo != null) return false;
        if (birthDateFrom != null ? !birthDateFrom.equals(that.birthDateFrom) : that.birthDateFrom != null)
            return false;
        if (birthDateTo != null ? !birthDateTo.equals(that.birthDateTo) : that.birthDateTo != null) return false;
        if (birthDay != null ? !birthDay.equals(that.birthDay) : that.birthDay != null) return false;
        if (birthMonth != null ? !birthMonth.equals(that.birthMonth) : that.birthMonth != null) return false;
        if (birthYear != null ? !birthYear.equals(that.birthYear) : that.birthYear != null) return false;
        if (birthplace != null ? !birthplace.equals(that.birthplace) : that.birthplace != null) return false;
        if (!Arrays.equals(cobrandingPartnerId, that.cobrandingPartnerId)) return false;
        if (documentNumber != null ? !documentNumber.equals(that.documentNumber) : that.documentNumber != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (infoSources != null ? !infoSources.equals(that.infoSources) : that.infoSources != null) return false;
        if (isBirthday != null ? !isBirthday.equals(that.isBirthday) : that.isBirthday != null) return false;
        if (lastActiveDateFrom != null ? !lastActiveDateFrom.equals(that.lastActiveDateFrom) : that.lastActiveDateFrom != null)
            return false;
        if (lastActiveDateTo != null ? !lastActiveDateTo.equals(that.lastActiveDateTo) : that.lastActiveDateTo != null)
            return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (!Arrays.equals(level, that.level)) return false;
        if (locality != null ? !locality.equals(that.locality) : that.locality != null) return false;
        if (mailer != null ? !mailer.equals(that.mailer) : that.mailer != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (partnerId != null ? !partnerId.equals(that.partnerId) : that.partnerId != null) return false;
        if (!Arrays.equals(programs, that.programs)) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (!Arrays.equals(registrationChannel, that.registrationChannel)) return false;
        if (registrationDateFrom != null ? !registrationDateFrom.equals(that.registrationDateFrom) : that.registrationDateFrom != null)
            return false;
        if (registrationDateTo != null ? !registrationDateTo.equals(that.registrationDateTo) : that.registrationDateTo != null)
            return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (swindler != null ? !swindler.equals(that.swindler) : that.swindler != null) return false;
        if (todayIsBirthday != null ? !todayIsBirthday.equals(that.todayIsBirthday) : that.todayIsBirthday != null)
            return false;
        if (transliterationMismatch != null ? !transliterationMismatch.equals(that.transliterationMismatch) : that.transliterationMismatch != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountNumber != null ? accountNumber.hashCode() : 0;
        result = 31 * result + (accountStatus != null ? Arrays.hashCode(accountStatus) : 0);
        result = 31 * result + (level != null ? Arrays.hashCode(level) : 0);
        result = 31 * result + (balanceFrom != null ? balanceFrom.hashCode() : 0);
        result = 31 * result + (balanceTo != null ? balanceTo.hashCode() : 0);
        result = 31 * result + (registrationDateFrom != null ? registrationDateFrom.hashCode() : 0);
        result = 31 * result + (registrationDateTo != null ? registrationDateTo.hashCode() : 0);
        result = 31 * result + (lastActiveDateFrom != null ? lastActiveDateFrom.hashCode() : 0);
        result = 31 * result + (lastActiveDateTo != null ? lastActiveDateTo.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (mailer != null ? mailer.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthDateFrom != null ? birthDateFrom.hashCode() : 0);
        result = 31 * result + (birthDateTo != null ? birthDateTo.hashCode() : 0);
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        result = 31 * result + (birthMonth != null ? birthMonth.hashCode() : 0);
        result = 31 * result + (birthYear != null ? birthYear.hashCode() : 0);
        result = 31 * result + (birthplace != null ? birthplace.hashCode() : 0);
        result = 31 * result + (todayIsBirthday != null ? todayIsBirthday.hashCode() : 0);
        result = 31 * result + (additionStatus != null ? Arrays.hashCode(additionStatus) : 0);
        result = 31 * result + (swindler != null ? swindler.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (locality != null ? locality.hashCode() : 0);
        result = 31 * result + (addressComment != null ? addressComment.hashCode() : 0);
        result = 31 * result + (transliterationMismatch != null ? transliterationMismatch.hashCode() : 0);
        result = 31 * result + (documentNumber != null ? documentNumber.hashCode() : 0);
        result = 31 * result + (registrationChannel != null ? Arrays.hashCode(registrationChannel) : 0);
        result = 31 * result + (partnerId != null ? partnerId.hashCode() : 0);
        result = 31 * result + (cobrandingPartnerId != null ? Arrays.hashCode(cobrandingPartnerId) : 0);
        result = 31 * result + (programs != null ? Arrays.hashCode(programs) : 0);
        result = 31 * result + (infoSources != null ? infoSources.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (isBirthday != null ? isBirthday.hashCode() : 0);
        return result;
    }

    public boolean isEmpty() {
        RichbannerClientFilter empty = new RichbannerClientFilter();
        return this.equals(empty);
    }
}
