package ru.fls.privateoffice.util.dto;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author YMolodkov
 * @since 16.0
 */
public class CobrandingElement {
    protected String clientIdFromPartner;
    protected PartnerElement partner;

    public String getClientIdFromPartner() {
        return clientIdFromPartner;
    }

    public void setClientIdFromPartner(String clientIdFromPartner) {
        this.clientIdFromPartner = clientIdFromPartner;
    }

    public PartnerElement getPartner() {
        return partner;
    }

    public void setPartner(PartnerElement partner) {
        this.partner = partner;
    }
}
