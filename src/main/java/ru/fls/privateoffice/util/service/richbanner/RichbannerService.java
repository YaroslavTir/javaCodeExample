package ru.fls.privateoffice.util.service.richbanner;

import org.springframework.web.multipart.MultipartFile;
import ru.fls.privateoffice.entity.BannerBase;
import ru.fls.privateoffice.entity.Richbanner;
import ru.fls.privateoffice.util.dto.*;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

/**
 * @author YMolodkov
 * @since 14.0
 */
public interface RichbannerService {

    void delete(Long id) throws PersistenceException;

    Richbanner getRichbanner(Long id);

    RichbannerDTO getRichbannerDTO(Long id);

    RichbannerDTO toRichbannerDTO(Richbanner richbanner) throws IOException;

    Richbanner toRichbanner(RichbannerDTO richbannerDTO);

    void saveRichbanner(RichbannerDTO richbannerDTO);

    void saveRichbanner(Richbanner richbanner, MultipartFile accountFile) throws IOException;

    Set<RichbannerViewDTO> getRichBannerViewSet(ProfilePageDTO page, AccountBalancePageDTO balance);

    List<RichbannerDTO> getAllRichbanners();

    List<RichbannerDTO> richbannerList(RichbannerFilter richbannerFilter);

    void incAccountExposure(Long bannerId, ProfilePageDTO page);

    void saveRichbannerAccounts(InputStream inputStream, BannerBase bannerBase) throws IOException;

}
