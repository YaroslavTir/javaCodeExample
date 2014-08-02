package ru.fls.privateoffice.util.service.richbanner;

import ru.fls.privateoffice.util.dto.AccountBalancePageDTO;
import ru.fls.privateoffice.util.dto.ProfilePageDTO;
import ru.fls.privateoffice.util.dto.RichbannerDTO;

import java.io.File;

/**
 * @author YMolodkov
 * @since 14.0
 */
public interface RichbannerFilter {

    boolean accept(RichbannerDTO richbannerDTO);

}
