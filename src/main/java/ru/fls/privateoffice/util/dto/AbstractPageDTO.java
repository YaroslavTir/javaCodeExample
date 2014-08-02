package ru.fls.privateoffice.util.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * User: NKarataeva
 * Date: 05.04.12
 * Time: 9:42
 */
public abstract class AbstractPageDTO implements PageDTO, Serializable {
    private final static String defaultDateTimeFormat = "dd.MM.yyyy";

    private ProcessingStatuses result;
    private String resultDescription;

    protected AbstractPageDTO() {
    }

    protected AbstractPageDTO(ProcessingStatuses result) {
        this.result = result;
    }

    public static SimpleDateFormat getFormatter() {
        return new SimpleDateFormat(defaultDateTimeFormat);
    }

    public static Gson getGson() {
        return getGsonWithDateFormat(defaultDateTimeFormat);
    }

    public static Gson getGsonWithDateFormat(String dateTimeFormat) {
        return new GsonBuilder().setDateFormat(dateTimeFormat).create();
    }

    @Override
    public ProcessingStatuses getResult() {
        return result;
    }

    public void setResult(ProcessingStatuses result) {
        this.result = result;
        if (result != null) {
            this.resultDescription = result.getClientDescription();
        }
    }

    @Override
    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }
}
