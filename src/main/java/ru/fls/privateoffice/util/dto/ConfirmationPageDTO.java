package ru.fls.privateoffice.util.dto;

import org.dozer.Mapping;

/**
 * User: NKarataeva
 * Date: 09.04.12
 * Time: 11:54
 */
public class ConfirmationPageDTO extends AbstractPageDTO {
    @Mapping(value = "result")
    private String operationResult;

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }
}
