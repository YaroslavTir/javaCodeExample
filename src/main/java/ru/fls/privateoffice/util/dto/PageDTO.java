package ru.fls.privateoffice.util.dto;

import ru.fls.privateoffice.util.codes.ProcessingStatuses;

/**
 * User: NKarataeva
 * Date: 05.04.12
 * Time: 9:39
 */
public interface PageDTO {
    public ProcessingStatuses getResult();
    public String getResultDescription();
}
