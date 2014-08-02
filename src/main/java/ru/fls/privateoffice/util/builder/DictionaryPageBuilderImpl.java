package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.DictionaryServiceWrapper;
import ru.fls.privateoffice.util.codes.DictionaryInfoCodes;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.DictionaryElementDTO;
import ru.fls.privateoffice.util.dto.DictionaryPageDTO;
import ru.rzd.loyalty.api.app.DictionaryElement;
import ru.rzd.loyalty.api.app.DictionaryInfoFault;
import ru.rzd.loyalty.api.app.DictionaryInfoResponse;
import ru.rzd.loyalty.shared.notify.MemcachedNotificationAware;


/**
 * User: NKarataeva
 * Date: 05.04.12
 * Time: 16:44
 */
@Component
public class DictionaryPageBuilderImpl implements DictionaryPageBuilder {
    private final static Logger log = Logger.getLogger(DictionaryPageBuilderImpl.class);
    @Autowired
    private DictionaryServiceWrapper dictionaryServiceWrapper;
    @Autowired
    ApplicationContext context;

    @MemcachedNotificationAware(group = "dictionary", key = "#{#p0.code}")
    @Cacheable(value = "dictionary_dto", key = "#p0.code+'_'+#p1")
    public DictionaryPageDTO buildDictionaryPageDTO(DictionaryInfoCodes dictionaryKey, String lang) {
        try {
            DictionaryPageDTO result = processDictionaryInfoResponse(dictionaryServiceWrapper.requestDictionary(dictionaryKey.getCode()), lang);
            log.info("Dictionary processing completed: dictionary '" + dictionaryKey.getCode() + "': " + result.getElements().size() + " records");
            return result;
        } catch (DictionaryInfoFault fault) {
            log.error("Dictionary service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            return processDictionaryInfoFault(fault);
        } catch (RuntimeException e) {
            log.error("Dictionary service error", e);
            return new DictionaryPageDTO(ProcessingStatuses.UNKNOWN_ERROR);
        }
    }

    @MemcachedNotificationAware(group = "dictionary", key = "#{#p0.code}")
    @Cacheable(value = "dictionary_dto", key = "#p0.code+'_old_'+#p1")
    public DictionaryPageDTO buildOldDictionaryPageDTO(DictionaryInfoCodes dictionaryKey, String lang) {
        try {
            DictionaryPageDTO result = processDictionaryInfoResponse(dictionaryServiceWrapper.requestOldDictionary(dictionaryKey.getCode()), lang);
            log.info("Dictionary (old) processing completed: dictionary '" + dictionaryKey.getCode() + "': " + result.getElements().size() + " records");
            return result;
        } catch (DictionaryInfoFault fault) {
            log.error("Dictionary service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            return processDictionaryInfoFault(fault);
        }
    }

    private DictionaryPageDTO processDictionaryInfoResponse(DictionaryInfoResponse dictionaryResponse, String lang) {
        DictionaryPageDTO result = new DictionaryPageDTO();
        if (dictionaryResponse != null && dictionaryResponse.getDictionaryElements() != null) {
            for (DictionaryElement element : dictionaryResponse.getDictionaryElements().getDictionaryElement()) {
                String value = "ru".equalsIgnoreCase(lang) || element.getEngValue() == null || element.getEngValue().isEmpty()
                        ? element.getValue() : element.getEngValue();

                result.addElement(new DictionaryElementDTO(element.getKey(), value, element.getDescription()));
            }
        }
        result.setResult(ProcessingStatuses.SUCCESS);
        return result;
    }

    private DictionaryPageDTO processDictionaryInfoFault(DictionaryInfoFault dictionaryFault) {
        DictionaryPageDTO result = new DictionaryPageDTO();
        try {
            result.setResult(ProcessingStatuses.getConstant(dictionaryFault.getFaultInfo().getCode()));
        } catch (Exception e) {
            result.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }
        return result;
    }
}
