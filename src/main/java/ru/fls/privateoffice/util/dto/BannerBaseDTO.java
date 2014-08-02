package ru.fls.privateoffice.util.dto;

import ru.fls.privateoffice.entity.types.BannerType;
import ru.fls.privateoffice.utils.DateFormatter;

import java.util.Arrays;
import java.util.Date;

/**
 * User: NKarataeva
 * Date: 19.12.12
 * Time: 12:18
 */
public abstract class BannerBaseDTO {

    protected DateFormatter dateFormatter= new DateFormatter();

    private Long id;
    private String name;

    private boolean active;
    private boolean currentActive;
    private Date activeFrom;
    private Date activeTo;
    private Integer exposures;

    private Integer weight;

    private BannerType bannerType;
    private Integer width;
    private Integer height;

    private String bannerPath;
    private String bannerAdditionalPath;
    private String bannerUrl;
    private String bannerText;

    private Long[] articleIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExposures() {
        return exposures;
    }

    public void setExposures(Integer exposures) {
        this.exposures = exposures;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom) {
        this.activeFrom = activeFrom;
    }

    public Date getActiveTo() {
        return activeTo;
    }

    public void setActiveTo(Date activeTo) {
        this.activeTo = activeTo;
    }

    public String getBannerText() {
        return bannerText;
    }

    public void setBannerText(String bannerText) {
        this.bannerText = bannerText;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public BannerType getBannerType() {
        return bannerType;
    }

    public void setBannerType(BannerType bannerType) {
        this.bannerType = bannerType;
    }

    public String getBannerPath() {
        return bannerPath;
    }

    public void setBannerPath(String bannerPath) {
        this.bannerPath = bannerPath;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getBannerAdditionalPath() {
        return bannerAdditionalPath;
    }

    public void setBannerAdditionalPath(String bannerAdditionalPath) {
        this.bannerAdditionalPath = bannerAdditionalPath;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public boolean isCurrentActive() {
        return currentActive;
    }

    public void setCurrentActive(boolean currentActive) {
        this.currentActive = currentActive;
    }

    public Long[] getArticleIds() {
        return articleIds;
    }

    public void setArticleIds(Long[] articleIds) {
        this.articleIds = articleIds;
    }

    public String getActiveFromString() {
        return dateFormatter.getDateAsString(activeFrom);
    }

    public void setActiveFromString(String dateString) {
        activeFrom = dateFormatter.parseDate(dateString);
    }

    public String getActiveToString() {
        return dateFormatter.getDateAsString(activeTo);
    }

    public void setActiveToString(String dateString) {
        activeTo = dateFormatter.parseDate(dateString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BannerBaseDTO)) return false;

        BannerBaseDTO that = (BannerBaseDTO) o;

        if (active != that.active) return false;
        if (currentActive != that.currentActive) return false;
        if (activeFrom != null ? !activeFrom.equals(that.activeFrom) : that.activeFrom != null) return false;
        if (activeTo != null ? !activeTo.equals(that.activeTo) : that.activeTo != null) return false;
        if (bannerAdditionalPath != null ? !bannerAdditionalPath.equals(that.bannerAdditionalPath) : that.bannerAdditionalPath != null)
            return false;
        if (bannerPath != null ? !bannerPath.equals(that.bannerPath) : that.bannerPath != null) return false;
        if (bannerText != null ? !bannerText.equals(that.bannerText) : that.bannerText != null) return false;
        if (bannerType != that.bannerType) return false;
        if (bannerUrl != null ? !bannerUrl.equals(that.bannerUrl) : that.bannerUrl != null) return false;
        if (exposures != null ? !exposures.equals(that.exposures) : that.exposures != null) return false;
        if (height != null ? !height.equals(that.height) : that.height != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (width != null ? !width.equals(that.width) : that.width != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (currentActive ? 1 : 0);
        result = 31 * result + (activeFrom != null ? activeFrom.hashCode() : 0);
        result = 31 * result + (activeTo != null ? activeTo.hashCode() : 0);
        result = 31 * result + (exposures != null ? exposures.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (bannerType != null ? bannerType.hashCode() : 0);
        result = 31 * result + (width != null ? width.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (bannerPath != null ? bannerPath.hashCode() : 0);
        result = 31 * result + (bannerAdditionalPath != null ? bannerAdditionalPath.hashCode() : 0);
        result = 31 * result + (bannerUrl != null ? bannerUrl.hashCode() : 0);
        result = 31 * result + (bannerText != null ? bannerText.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BannerBaseDTO{" +
                "dateFormatter=" + dateFormatter +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", currentActive=" + currentActive +
                ", activeFrom=" + activeFrom +
                ", activeTo=" + activeTo +
                ", exposures=" + exposures +
                ", weight=" + weight +
                ", bannerType=" + bannerType +
                ", width=" + width +
                ", height=" + height +
                ", bannerPath='" + bannerPath + '\'' +
                ", bannerAdditionalPath='" + bannerAdditionalPath + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", bannerText='" + bannerText + '\'' +
                ", articleIds=" + Arrays.toString(articleIds) +
                '}';
    }
}
