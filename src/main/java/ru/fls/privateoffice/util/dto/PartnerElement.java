package ru.fls.privateoffice.util.dto;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author YMolodkov
 * @since 16.0
 */
public class PartnerElement {
    protected long id;
    protected String code;
    protected String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
