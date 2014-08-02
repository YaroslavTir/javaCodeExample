package ru.fls.privateoffice.util.dto;

import org.dozer.Mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * User: NKarataeva
 * Date: 05.04.12
 * Time: 12:43
 */
public class OperationsHistoryPageDTO extends AbstractPageDTO {
    @Mapping(value = "accountHistoryEvent")
    private List<OperationHistoryElementDTO> elements = new ArrayList<OperationHistoryElementDTO>();

    public void addElement(OperationHistoryElementDTO element) {
        elements.add(element);
    }

    public List<OperationHistoryElementDTO> getElements() {
        return elements;
    }

    public String getElementsJson() {
        if (elements == null) {
            elements = new ArrayList<OperationHistoryElementDTO>();
        }

        // Hide from user information about charge-off
        for (OperationHistoryElementDTO element : elements)
            element.setOperation(element.getOperation().replaceFirst("Идентификатор операции списания: \\S+\\s+", ""));

        return getGsonWithDateFormat("dd.MM.yyyy HH:mm:ss").toJson(elements);
    }

}
