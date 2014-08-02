package ru.fls.privateoffice.util.builder;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.CardServiceWrapper;
import ru.fls.privateoffice.util.dto.CardsInfoDTO;
import ru.rzd.loyalty.api.app.CardsInfoResponse;

/**
 * User: ABolotov
 * Date: 22.01.14
 * Time: 15:45
 */
@Component
public class CardsInfoBuilder extends AuthorizedPageBuilder {

    @Autowired
    private CardServiceWrapper cardServiceWrapper;
    @Autowired
    private Mapper mapper;

    public CardsInfoDTO build() {
        CardsInfoResponse cardsInfoResponse = cardServiceWrapper.cardsInfo(getSessionId());
        CardsInfoDTO cardsInfoDTO = mapper.map(cardsInfoResponse, CardsInfoDTO.class);
        return cardsInfoDTO;
    }
}
