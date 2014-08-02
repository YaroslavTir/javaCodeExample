package ru.fls.privateoffice.util.service.richbanner;

import ru.fls.privateoffice.util.dto.banner.RichbannerDTO;

/**
 * @author YMolodkov
 * @since 14.0
 */
final public class RichbannerAllFilter implements RichbannerFilter {
    @Override
    public boolean accept(RichbannerDTO richbannerDTO) {
        return true;
    }
}
