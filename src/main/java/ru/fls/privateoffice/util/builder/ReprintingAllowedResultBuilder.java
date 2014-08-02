package ru.fls.privateoffice.util.builder;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.CardServiceWrapper;
import ru.fls.privateoffice.util.dto.ReprintingAllowedResultDTO;
import ru.rzd.loyalty.api.app.CardDuplicateCheckResponse;
import ru.rzd.loyalty.api.app.CardDuplicateResponse;

/**
 * User: NFadin
 * Date: 23.01.14
 * Time: 13:08
 */
@Component
public class ReprintingAllowedResultBuilder extends AuthorizedPageBuilder {
    @Autowired
    private CardServiceWrapper cardServiceWrapper;
    @Autowired
    private Mapper mapper;

    public ReprintingAllowedResultDTO buildCheckDuplicateResult() {
        CardDuplicateCheckResponse response = cardServiceWrapper.checkCardDuplicate(getSessionId());
        return mapper.map(response.getResult(), ReprintingAllowedResultDTO.class);
    }

    public ReprintingAllowedResultDTO buildDuplicateResult(String reason) {
        CardDuplicateResponse response = cardServiceWrapper.cardDuplicate(getSessionId(), reason);
        return mapper.map(response.getResult(), ReprintingAllowedResultDTO.class);
    }
}
