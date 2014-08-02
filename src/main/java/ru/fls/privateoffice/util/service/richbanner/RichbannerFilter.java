package ru.fls.privateoffice.util.service.richbanner;

import ru.fls.privateoffice.util.dto.banner.RichbannerDTO;

/**
 * @author YMolodkov
 * @since 14.0
 */
public interface RichbannerFilter {

    boolean accept(RichbannerDTO richbannerDTO);

}
