package ru.fls.privateoffice.util.dto;

import org.dozer.Mapping;

import java.util.List;

/**
 * User: ABolotov
 * Date: 22.01.14
 * Time: 15:47
 */
public class CardsInfoDTO extends AbstractPageDTO {
    @Mapping("cardInfo")
    private List<CardInfoDTO> cardInfoList;

    public List<CardInfoDTO> getCardInfoList() {
        return cardInfoList;
    }

    public void setCardInfoList(List<CardInfoDTO> cardInfoList) {
        this.cardInfoList = cardInfoList;
    }

    public String getListJson() {
        return getGson().toJson(cardInfoList);
    }
}
