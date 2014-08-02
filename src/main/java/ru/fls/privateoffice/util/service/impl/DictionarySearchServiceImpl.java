package ru.fls.privateoffice.util.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.entity.Dictionary;
import ru.fls.privateoffice.util.builder.DictionaryPageBuilder;
import ru.fls.privateoffice.util.codes.DictionaryInfoCodes;
import ru.fls.privateoffice.util.dto.DictionaryElementDTO;
import ru.fls.privateoffice.util.dto.DictionaryPageDTO;
import ru.fls.privateoffice.util.service.DictionarySearchService;
import ru.rzd.loyalty.shared.utils.LangUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: AKosarenko
 * Date: 19.10.12 13:29
 */
@Component
public class DictionarySearchServiceImpl implements DictionarySearchService {
    private final static Logger log = Logger.getLogger(DictionarySearchServiceImpl.class);

    public static final int SEARCH_BY_KEY = 1;
    public static final int SEARCH_BY_VALUE = 2;
    public static final int SEARCH_BY_DESCRIPTION = 3;

    public static final int RETURN_KEY = 4;
    public static final int RETURN_VALUE = 5;
    public static final int RETURN_DESCRIPTION = 6;

    @Autowired
    private DictionaryPageBuilder dictionaryPageBuilder;

    public DictionaryElementDTO getDictionaryElementByKey(DictionaryInfoCodes dictCode, String key, String lang) {
        log.info("Get Dictionary Element By Key: " + dictCode + ", " + key);
        if (StringUtils.isNotBlank(key)) {
            for (DictionaryElementDTO dictionaryElementDTO : getDictionaryElements(dictCode, lang)) {
                if (key.equals(dictionaryElementDTO.getKey())) {
                    return dictionaryElementDTO;
                }
            }
        }
        return null;
    }

    public DictionaryElementDTO getDictionaryElementByValue(DictionaryInfoCodes dictCode, String value, String lang) {
        log.info("Get Dictionary Element By Value: " + dictCode + ", " + value);
        if (StringUtils.isNotBlank(value)) {
            for (DictionaryElementDTO dictionaryElementDTO : getDictionaryElements(dictCode, lang)) {
                if (value.equals(dictionaryElementDTO.getValue())) {
                    return dictionaryElementDTO;
                }
            }
        }
        return null;
    }

    public DictionaryElementDTO searchDictionaryElementByValue(DictionaryInfoCodes dictCode, String value, String lang) {
        if (value != null) {
            for (DictionaryElementDTO dictionaryElementDTO : getDictionaryElements(dictCode, lang)) {
                if (value.equalsIgnoreCase(dictionaryElementDTO.getValue())) {
                    return dictionaryElementDTO;
                }
            }
        }
        return null;
    }

    public DictionaryElementDTO getDictionaryElementByDescription(DictionaryInfoCodes dictCode, String desc, String lang) {
        log.info("Get Dictionary Element By Description: " + dictCode + ", " + desc);
        if (StringUtils.isNotBlank(desc)) {
            for (DictionaryElementDTO dictionaryElementDTO : getDictionaryElements(dictCode, lang)) {
                if (desc.equals(dictionaryElementDTO.getDescription())) {
                    return dictionaryElementDTO;
                }
            }
        }
        return null;
    }

    public DictionaryPageDTO getDictionaryPageDTO(DictionaryInfoCodes dictCode, String lang) {
        log.info("Get Dictionary Page DTO: " + dictCode);
        return dictionaryPageBuilder.buildDictionaryPageDTO(dictCode, lang);
    }

    public List<DictionaryElementDTO> getDictionaryElements(DictionaryInfoCodes dictCode, String lang) {
        log.info("Get Dictionary Elements: " + dictCode);
        DictionaryPageDTO dictionaryPage = getDictionaryPageDTO(dictCode, lang);
        return (dictionaryPage != null) ? dictionaryPage.getElements() : new ArrayList<DictionaryElementDTO>();
    }

    public List<DictionaryElementDTO> getDictionaryElementsByValue(DictionaryInfoCodes dictCode, String value, String lang) {
        log.info("Get Dictionary Elements: " + dictCode + ", " + value);
        DictionaryPageDTO dictionaryPageDTO = getDictionaryPageDTO(dictCode, lang);
        if (StringUtils.isNotBlank(value)) {
            value = value.toLowerCase();
            List<DictionaryElementDTO> result = new ArrayList<DictionaryElementDTO>();
            for (DictionaryElementDTO elementDTO : dictionaryPageDTO.getElements()) {
                if (elementDTO.getValue() != null && elementDTO.getValue().toLowerCase().startsWith(value)) {
                    result.add(elementDTO);
                }
            }
            return result;
        } else {
            return dictionaryPageDTO.getElements();
        }
    }

    public String searchDictionaryElement(DictionaryInfoCodes dictCode, String searchStr, int searchType, int returnType, String lang) {
        String resp = null;
        DictionaryElementDTO elem = null;
        switch (searchType) {
            case SEARCH_BY_KEY:
                elem = getDictionaryElementByKey(dictCode, searchStr, lang);
                break;
            case SEARCH_BY_VALUE:
                elem = searchDictionaryElementByValue(dictCode, searchStr, lang);
                break;
            case SEARCH_BY_DESCRIPTION:
                elem = getDictionaryElementByDescription(dictCode, searchStr, lang);
                break;
        }

        if (elem != null) {
            switch (returnType) {
                case RETURN_KEY:
                    resp = elem.getKey();
                    break;
                case RETURN_VALUE:
                    resp = elem.getValue();
                    break;
                case RETURN_DESCRIPTION:
                    resp = elem.getDescription();
                    break;
            }
        }

        return resp;
    }

    public List<Dictionary> getDictionaryByValue(DictionaryInfoCodes dictCode, String value, String lang) {
        String langFromTerm = LangUtils.getTermLang(value);
        if (langFromTerm != null) {
            lang = langFromTerm;
        }

        List<DictionaryElementDTO> dictionaryElementDTOs = getDictionaryElementsByValue(dictCode, value, lang);

        List<Dictionary> result = new ArrayList<Dictionary>();

        if (dictionaryElementDTOs != null && !dictionaryElementDTOs.isEmpty()) {
            for (DictionaryElementDTO elementDTO : dictionaryElementDTOs) {
                result.add(new Dictionary(dictCode.getCode(), elementDTO.getKey(), elementDTO.getValue(), elementDTO.getDescription()));
            }
        }
        return result;
    }
}
