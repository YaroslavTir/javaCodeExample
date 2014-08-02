package ru.fls.privateoffice.util.dto;

import org.dozer.Mapping;

/**
 * User: NKarataeva
 * Date: 16.04.12
 * Time: 16:49
 */
public class JourneyDataEnteringResultDTO extends AbstractPageDTO {
    @Mapping(value = "result")
    private String operationResult;

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }
}
