package ru.fls.privateoffice.util.service.richbanner;

import ru.fls.privateoffice.util.dto.AccountBalancePageDTO;
import ru.fls.privateoffice.util.dto.ProfilePageDTO;
import ru.fls.privateoffice.util.dto.banner.RichbannerDTO;

import java.util.List;
import java.util.Set;

/**
 * @author YMolodkov
 * @since 16.0
 */
public interface RichbannerEngine {

    public List<RichbannerDTO> richbannerList(RichbannerFilter richbannerFilter, List<RichbannerDTO> allRichbanners);

}
