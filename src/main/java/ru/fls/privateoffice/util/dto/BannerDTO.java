package ru.fls.privateoffice.util.dto;

import ru.fls.privateoffice.entity.types.PlaceType;
import ru.fls.privateoffice.entity.types.UserGender;
import ru.fls.privateoffice.entity.types.UserState;

import java.util.Date;

/**
 * @author YMolodkov
 * @since 14.0
 */
public class BannerDTO extends BannerBaseDTO {

    private PlaceType placeType;
    protected Date lastExposureDate;
    protected Integer currentExposuresPerDay;
    protected UserGender userGender;
    private String partnerCode;
    private Integer currentExposures;

    private Integer exposuresPerDay;
    private Integer transitions;
    private UserState userState;
    private Long[] regionIds;
    private Long[] localityIds;

    public PlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }

    public String getLastExposureDateString() {
        return dateFormatter.getDateAsString(lastExposureDate);
    }

    public void setLastExposureDateString(String dateString) {
        lastExposureDate = dateFormatter.parseDate(dateString);
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public Integer getCurrentExposuresPerDay() {
        return currentExposuresPerDay;
    }

    public void setCurrentExposuresPerDay(Integer currentExposuresPerDay) {
        this.currentExposuresPerDay = currentExposuresPerDay;
    }

    public UserGender getUserGender() {
        return userGender;
    }

    public void setUserGender(UserGender userGender) {
        this.userGender = userGender;
    }

public Integer getExposuresPerDay() {
        return exposuresPerDay;
    }

    public void setExposuresPerDay(Integer exposuresPerDay) {
        this.exposuresPerDay = exposuresPerDay;
    }

    public Integer getTransitions() {
        return transitions;
    }

    public void setTransitions(Integer transitions) {
        this.transitions = transitions;
    }

    public Date getLastExposureDate() {
        return lastExposureDate;
    }

    public void setLastExposureDate(Date lastExposureDate) {
        this.lastExposureDate = lastExposureDate;
    }

    public Integer getCurrentExposures() {
        return currentExposures;
    }

    public void setCurrentExposures(Integer currentExposures) {
        this.currentExposures = currentExposures;
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public Long[] getRegionIds() {
        return regionIds;
    }

    public void setRegionIds(Long[] regionIds) {
        this.regionIds = regionIds;
    }

    public Long[] getLocalityIds() {
        return localityIds;
    }

    public void setLocalityIds(Long[] localityIds) {
        this.localityIds = localityIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BannerDTO)) return false;
        if (!super.equals(o)) return false;

        BannerDTO bannerDTO = (BannerDTO) o;

        if (currentExposures != null ? !currentExposures.equals(bannerDTO.currentExposures) : bannerDTO.currentExposures != null)
            return false;
        if (currentExposuresPerDay != null ? !currentExposuresPerDay.equals(bannerDTO.currentExposuresPerDay) : bannerDTO.currentExposuresPerDay != null)
            return false;
        if (exposuresPerDay != null ? !exposuresPerDay.equals(bannerDTO.exposuresPerDay) : bannerDTO.exposuresPerDay != null)
            return false;
        if (lastExposureDate != null ? !lastExposureDate.equals(bannerDTO.lastExposureDate) : bannerDTO.lastExposureDate != null)
            return false;
        if (partnerCode != null ? !partnerCode.equals(bannerDTO.partnerCode) : bannerDTO.partnerCode != null)
            return false;
        if (placeType != bannerDTO.placeType) return false;
        if (transitions != null ? !transitions.equals(bannerDTO.transitions) : bannerDTO.transitions != null)
            return false;
        if (userGender != bannerDTO.userGender) return false;
        if (userState != bannerDTO.userState) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (placeType != null ? placeType.hashCode() : 0);
        result = 31 * result + (lastExposureDate != null ? lastExposureDate.hashCode() : 0);
        result = 31 * result + (currentExposuresPerDay != null ? currentExposuresPerDay.hashCode() : 0);
        result = 31 * result + (userGender != null ? userGender.hashCode() : 0);
        result = 31 * result + (partnerCode != null ? partnerCode.hashCode() : 0);
        result = 31 * result + (currentExposures != null ? currentExposures.hashCode() : 0);
        result = 31 * result + (exposuresPerDay != null ? exposuresPerDay.hashCode() : 0);
        result = 31 * result + (transitions != null ? transitions.hashCode() : 0);
        result = 31 * result + (userState != null ? userState.hashCode() : 0);
        return result;
    }
}
