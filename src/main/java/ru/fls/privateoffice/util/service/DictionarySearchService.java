package ru.fls.privateoffice.util.service;

import ru.fls.privateoffice.entity.Dictionary;
import ru.fls.privateoffice.util.codes.DictionaryInfoCodes;
import ru.fls.privateoffice.util.dto.DictionaryElementDTO;
import ru.fls.privateoffice.util.dto.DictionaryPageDTO;

import java.util.List;

/**
 * User: DDenisov
 * Date: 29.11.13 18:19
 */
public interface DictionarySearchService {
    DictionaryElementDTO getDictionaryElementByKey(DictionaryInfoCodes dictCode, String key, String lang);

    DictionaryElementDTO getDictionaryElementByValue(DictionaryInfoCodes dictCode, String value, String lang);

    DictionaryElementDTO searchDictionaryElementByValue(DictionaryInfoCodes dictCode, String value, String lang);

    DictionaryElementDTO getDictionaryElementByDescription(DictionaryInfoCodes dictCode, String desc, String lang);

    DictionaryPageDTO getDictionaryPageDTO(DictionaryInfoCodes dictCode, String lang);

    List<DictionaryElementDTO> getDictionaryElements(DictionaryInfoCodes dictCode, String lang);

    List<DictionaryElementDTO> getDictionaryElementsByValue(DictionaryInfoCodes dictCode, String value, String lang);

    String searchDictionaryElement(DictionaryInfoCodes dictCode, String searchStr, int searchType, int returnType, String lang);

    List<Dictionary> getDictionaryByValue(DictionaryInfoCodes dictCode, String value, String lang);
}
