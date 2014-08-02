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
public class DictionaryPageDTO extends AbstractPageDTO {
    @Mapping(value = "dictionaryElement")
    private List<DictionaryElementDTO> elements = new ArrayList<DictionaryElementDTO>();

    public DictionaryPageDTO() {
    }

    public DictionaryPageDTO(ProcessingStatuses result) {
        super(result);
    }

    public void addElement(DictionaryElementDTO element) {
        elements.add(element);
    }

    public List<DictionaryElementDTO> getElements() {
        if (elements == null) {
            elements = new ArrayList<DictionaryElementDTO>();
        }
        return elements;
    }

    public String getElementsJson2() {
        return getGson().toJson(getElements());
    }

    public String getElementsJson() {
        StringBuilder builder = new StringBuilder();
        for (DictionaryElementDTO d : getElements()) {
            builder.append(";");
            builder.append(d.getKey());
            builder.append(":");
            builder.append(d.getValue());
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(0);
        }

        return builder.toString();
    }
}
