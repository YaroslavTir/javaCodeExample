package ru.fls.privateoffice.util.dto;

import ru.fls.privateoffice.util.service.richbanner.RichbannerClientFilter;

/**
 * @author YMolodkov
 * @since 14.0
 */
public class RichbannerDTO extends BannerBaseDTO {

    private RichbannerClientFilter filter;

    private Boolean firstVisit;

    private String accountFileName;

    private Boolean customSize;

    public RichbannerClientFilter getFilter() {
        return filter;
    }

    public void setFilter(RichbannerClientFilter filter) {
        this.filter = filter;
    }

    public Boolean getFirstVisit() {
        return firstVisit;
    }

    public void setFirstVisit(Boolean firstVisit) {
        this.firstVisit = firstVisit;
    }

    public String getAccountFileName() {
        return accountFileName;
    }

    public void setAccountFileName(String accountFileName) {
        this.accountFileName = accountFileName;
    }

    public Boolean getCustomSize() {
        return customSize;
    }

    public void setCustomSize(Boolean customSize) {
        this.customSize = customSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RichbannerDTO)) return false;
        if (!super.equals(o)) return false;

        RichbannerDTO that = (RichbannerDTO) o;

        if (accountFileName != null ? !accountFileName.equals(that.accountFileName) : that.accountFileName != null)
            return false;
        if (filter != null ? !filter.equals(that.filter) : that.filter != null) return false;
        if (firstVisit != null ? !firstVisit.equals(that.firstVisit) : that.firstVisit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (filter != null ? filter.hashCode() : 0);
        result = 31 * result + (firstVisit != null ? firstVisit.hashCode() : 0);
        result = 31 * result + (accountFileName != null ? accountFileName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "RichbannerDTO{" +
                "filter=" + filter +
                ", firstVisit=" + firstVisit +
                ", accountFileName='" + accountFileName + '\'' +
                ", customSize=" + customSize +
                '}';
    }

}
