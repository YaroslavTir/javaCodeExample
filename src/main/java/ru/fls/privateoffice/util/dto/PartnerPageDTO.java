package ru.fls.privateoffice.util.dto;

import org.dozer.Mapping;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;

import java.util.ArrayList;
import java.util.List;

/**
 * User: NKarataeva
 * Date: 05.04.12
 * Time: 16:47
 */
public class PartnerPageDTO extends AbstractPageDTO {
    @Mapping(value = "partnerElement")
    private List<PartnerElementDTO> elements = new ArrayList<PartnerElementDTO>();

    public PartnerPageDTO() {
    }

    public PartnerPageDTO(ProcessingStatuses result) {
        super(result);
    }

    public void addElement(PartnerElementDTO element) {
        elements.add(element);
    }

    public List<PartnerElementDTO> getElements() {
        if (elements == null) {
            elements = new ArrayList<PartnerElementDTO>();
        }
        return elements;
    }

    public String getElementsJson2() {
        return getGson().toJson(getElements());
    }

    public String getElementsJson() {
        StringBuilder builder = new StringBuilder();
        for (PartnerElementDTO d : getElements()) {
            builder.append(";");
            builder.append(d.getCode());
            builder.append(":");
            builder.append(d.getName());
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(0);
        }

        return builder.toString();
    }
}
