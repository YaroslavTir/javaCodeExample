package ru.fls.privateoffice.util.dto;

import org.dozer.Mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * User: NKarataeva
 * Date: 16.04.12
 * Time: 15:21
 */
public class JourneyDataEnteringPageDTO extends AbstractPageDTO {
    @Mapping(value = "journeyDataEnteringEvent")
    private List<JourneyDataEnteringElementDTO> elements = new ArrayList<JourneyDataEnteringElementDTO>();

    public void addElement(JourneyDataEnteringElementDTO element) {
        elements.add(element);
    }

    public List<JourneyDataEnteringElementDTO> getElements() {
        return elements;
    }

    public void setElements(List<JourneyDataEnteringElementDTO> elements) {
        this.elements = elements;
    }

    public String getElementsJson() {
        if (elements == null) {
            elements = new ArrayList<JourneyDataEnteringElementDTO>();
        }
        return getGson().toJson(elements);
    }
}
