package ru.fls.privateoffice.util.dto;

/**
 * User: NKarataeva
 * Date: 05.04.12
 * Time: 16:48
 */
public class DictionaryElementDTO {

    public DictionaryElementDTO() {
    }

    public DictionaryElementDTO(String key, String value, String description) {
        this.key = key;
        this.value = value;
        this.description = description;
    }

    private String key;
    private String value;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DictionaryElementDTO)) return false;

        DictionaryElementDTO that = (DictionaryElementDTO) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
