package ru.fls.privateoffice.util.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import ru.fls.privateoffice.util.codes.CodeEnum;
import ru.fls.privateoffice.util.codes.CodeEnumWithDescription;

/**
 * User: NFadin
 * Date: 17.08.12
 * Time: 17:53
 */
public class TitleService {

    @Autowired
    @Qualifier("messageSource")
    private MessageSource messageSource;

//    private static Locale locale = new Locale("ru", "RU");

    public String getMessage(String prop, String lang, boolean suffixEnabled) {
        String title = "";
        Locale locale=new Locale(lang);
        try {
            title = messageSource.getMessage(prop, null, locale);
            if (suffixEnabled)
                title += " " + messageSource.getMessage("headerTitle.suffix", null, locale);
        } catch (NoSuchMessageException e1) {
            if (!prop.equals("headerTitle.suffix")) {
                try {
                    title = messageSource.getMessage("headerTitle.default", null, locale);
                } catch (NoSuchMessageException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return title;
    }
    
    public String getCodeEnumMessage(CodeEnum c, String lang){
    	String msg = "";
    	String code=String.format("%s.%s",c.getClass().getSimpleName(), c.getCode().replace(' ', '_'));
        Locale locale=new Locale(lang);
        try {
            msg = messageSource.getMessage(code, null, locale);
        } catch (NoSuchMessageException e1) {
                try {
                	code=c.getClass().getSimpleName()+".default";
                    msg = messageSource.getMessage(code, null, locale);
                } catch (NoSuchMessageException e2) {
                    e2.printStackTrace();
                }
        }
        return msg;
    }
    
    public String getStatusMessage(CodeEnumWithDescription status, String lang){
    	String msg = "";
    	String code=String.format("%s.%s",status.getClass().getSimpleName(), status.getStatusDescription().replace(' ', '_'));
        Locale locale=new Locale(lang);
        try {
            msg = messageSource.getMessage(code, null, locale);
        } catch (NoSuchMessageException e1) {
                try {
                	code=status.getClass().getSimpleName()+".default";
                    msg = messageSource.getMessage(code, null, locale);
                } catch (NoSuchMessageException e2) {
                    e2.printStackTrace();
                }
        }
        return msg;
    }
}
