package ru.fls.privateoffice.util.dto;

import org.dozer.Mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * User: NKarataeva
 * Date: 05.04.12
 * Time: 13:09
 */
public class LevelChangesHistoryPageDTO extends AbstractPageDTO {

    @Mapping(value = "participationLevelChange")
    private List<LevelChangesHistoryElementDTO> elements = new ArrayList<LevelChangesHistoryElementDTO>();

    public void addElement(LevelChangesHistoryElementDTO element) {
        elements.add(element);
    }

    public List<LevelChangesHistoryElementDTO> getElements() {
        return elements;
    }

    public String getElementsJson() {
        return getGson().toJson(elements);
    }
}
