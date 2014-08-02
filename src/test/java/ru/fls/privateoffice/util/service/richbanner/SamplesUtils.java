package ru.fls.privateoffice.util.service.richbanner;

import ru.fls.privateoffice.util.dto.banner.RichbannerDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YMolodkov
 * @since 16.0
 */
public class SamplesUtils {
    public static RichbannerDTO fillRichbannerDTO(RichbannerDTO richbannerDTO) {
        RichbannerClientFilter richbannerClientFilter = new RichbannerClientFilter();
        Map<String, Integer> accountExposures = new HashMap<String, Integer>();
        richbannerClientFilter.setAccountNumber("1");
        accountExposures.put("1", 1);
        accountExposures.put("2", 2);
        accountExposures.put("3", 3);
        richbannerDTO.setId(1L);
        richbannerDTO.setFilter(richbannerClientFilter);
        return richbannerDTO;
    }
}
