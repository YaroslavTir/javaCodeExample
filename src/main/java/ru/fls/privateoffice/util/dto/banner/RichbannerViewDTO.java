package ru.fls.privateoffice.util.dto.banner;

import java.io.Serializable;

public class RichbannerViewDTO implements Serializable, Comparable<RichbannerViewDTO> {

    private Long id;
    private String bannerClickUrl;
    private Integer weight;
    private String bannerPath;
    private String additionalBannerPath;

    private Boolean customSize;
    private Integer width;
    private Integer height;

    public RichbannerViewDTO() {
    }

    public static RichbannerViewDTO create(RichbannerDTO dto) {
        final RichbannerViewDTO result = new RichbannerViewDTO();
        result.setId(dto.getId());
        result.setBannerClickUrl(dto.getBannerUrl());
        result.setWeight(dto.getWeight());
        result.setBannerPath(dto.getBannerPath());
        result.setAdditionalBannerPath(dto.getBannerAdditionalPath());
        result.setCustomSize(dto.getCustomSize());
        result.setWidth(dto.getWidth());
        result.setHeight(dto.getHeight());
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBannerClickUrl() {
        return bannerClickUrl;
    }

    public void setBannerClickUrl(String bannerClickUrl) {
        this.bannerClickUrl = bannerClickUrl;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getBannerPath() {
        return bannerPath;
    }

    public void setBannerPath(String bannerPath) {
        this.bannerPath = bannerPath;
    }

    public String getAdditionalBannerPath() {
        return additionalBannerPath;
    }

    public Boolean getCustomSize() {
        return customSize;
    }

    public void setCustomSize(Boolean customSize) {
        this.customSize = customSize;
    }

    public void setAdditionalBannerPath(String additionalBannerPath) {
        this.additionalBannerPath = additionalBannerPath;
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

    @Override
    public int compareTo(RichbannerViewDTO o) {
        Integer w1 = o.getWeight();
        Integer w2 = this.getWeight();
        if (w1 == null && w2 == null) return o.getId().compareTo(this.getId());
        if (w1 == null) return -1;
        if (w2 == null) return 1;
        int res = w1.compareTo(w2);
        return res!=0?res:o.getId().compareTo(this.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RichbannerViewDTO that = (RichbannerViewDTO) o;

        if (additionalBannerPath != null ? !additionalBannerPath.equals(that.additionalBannerPath) : that.additionalBannerPath != null)
            return false;
        if (bannerClickUrl != null ? !bannerClickUrl.equals(that.bannerClickUrl) : that.bannerClickUrl != null)
            return false;
        if (bannerPath != null ? !bannerPath.equals(that.bannerPath) : that.bannerPath != null) return false;
        if (height != null ? !height.equals(that.height) : that.height != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (width != null ? !width.equals(that.width) : that.width != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bannerClickUrl != null ? bannerClickUrl.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (bannerPath != null ? bannerPath.hashCode() : 0);
        result = 31 * result + (additionalBannerPath != null ? additionalBannerPath.hashCode() : 0);
        result = 31 * result + (width != null ? width.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        return result;
    }
}
