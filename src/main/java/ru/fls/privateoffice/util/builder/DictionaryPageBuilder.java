package ru.fls.privateoffice.util.builder;

import ru.fls.privateoffice.util.codes.DictionaryInfoCodes;
import ru.fls.privateoffice.util.dto.DictionaryPageDTO;


/**
 * User: NKarataeva
 * Date: 05.04.12
 * Time: 16:44
 */
public interface DictionaryPageBuilder {
    DictionaryPageDTO buildDictionaryPageDTO(DictionaryInfoCodes dictionaryKey, String lang);

    DictionaryPageDTO buildOldDictionaryPageDTO(DictionaryInfoCodes dictionaryKey, String lang);
}
