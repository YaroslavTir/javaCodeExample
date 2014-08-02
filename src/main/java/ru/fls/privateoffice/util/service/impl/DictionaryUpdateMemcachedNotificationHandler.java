package ru.fls.privateoffice.util.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import ru.fls.privateoffice.daoservice.LangService;
import ru.rzd.loyalty.shared.notify.MemcachedNotificationHandler;

/**
 * User: DDenisov
 * Date: 28.11.13 15:14
 */
public class DictionaryUpdateMemcachedNotificationHandler implements MemcachedNotificationHandler {
    @Autowired
    LangService langService;
    @Autowired
    CacheManager cacheManager;

    @Override
    public void handleNotification(String key) {
        Cache dictionaryCache = cacheManager.getCache("dictionary");
        dictionaryCache.evict(key);
        dictionaryCache.evict(key + "_old");

        Cache dictionaryDtoCache = cacheManager.getCache("dictionary_dto");
        for (String lang : langService.getAvailableLanguages()) {
            dictionaryDtoCache.evict(key + "_" + lang);
            dictionaryDtoCache.evict(key + "_old_" + lang);
        }
    }
}
