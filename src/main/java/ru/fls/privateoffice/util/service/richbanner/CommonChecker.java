package ru.fls.privateoffice.util.service.richbanner;

import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author YMolodkov
 * @since 14.0
 */
@Component
public class CommonChecker {

    public String replaceSpecialSymbols(String queryString) {
        if (queryString == null) return null;
        String result = queryString.trim();
        boolean isUnderscoreEnd = result.charAt(result.length() - 1) == '_';
        if (isUnderscoreEnd) result = result.substring(0, result.length() - 1);
        result = "\\Q" + result.trim() + "\\E";
        if (queryString != null) {
            result = result
                    .replaceAll("[*]", "\\\\E(.*)\\\\Q")
                    .replaceAll("[?]", "\\\\E(.{1})\\\\Q");
            if (!isUnderscoreEnd) result = result + "(.*)";
        }
        return result;
    }

    public String replaceSpecialCharacters(String value) {
        if (value == null) return value;
        String result = value.toUpperCase();
        result = result.replace('Ё', 'Е');
        return result;
    }

    public boolean arrStringCheck(String[] mask, String value) {
        if (mask == null || mask.length == 0) return true;
        return listStringCheck(Arrays.asList(mask), value);
    }

    public boolean listStringCheck(List<String> mask, String value) {
        if (mask == null || mask.size() == 0) return true;
        return mask.contains(value);
    }

    public boolean strByFormatCheck(String mask, String value) {
        if (mask == null || mask.isEmpty()) return true;
        if (value == null) return false;
        mask = replaceSpecialSymbols(mask);
        Pattern p = Pattern.compile(mask, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        return p.matcher(value).matches();
    }

    public boolean stringCheck(String mask, String value) {
        if (mask == null || mask.isEmpty()) return true;
        if (value == null) return false;
        return mask.equals(value);
    }

    public boolean fioStringCheck(String mask, String value) {
        if (mask == null || mask.isEmpty()) return true;
        if (value == null) return false;
        mask = replaceSpecialCharacters(replaceSpecialSymbols(mask));
        Pattern p = Pattern.compile(mask, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        return p.matcher(replaceSpecialCharacters(value)).matches();
    }


    public boolean booleanCheck(Boolean mask, Boolean value) {
        if (mask == null) return true;
        return mask.equals(value);
    }

    public boolean stringListCheck(String mask, List<String> value) {
        if (mask == null || mask.isEmpty()) return true;
        if (value == null) return false;
        return value.contains(mask);
    }

    public boolean dateFromCheck(Date mask, Date value) {
        if (mask == null) return true;
        if (value == null) return false;
        LocalDate activeFromLocal = new LocalDate(mask);
        return value.getTime() >= activeFromLocal.toDate().getTime();
    }

    public boolean dateToCheck(Date mask, Date value) {
        if (mask == null) return true;
        if (value == null) return false;
        LocalDate activeToLocal = new LocalDate(mask);
        return value.getTime() < activeToLocal.withFieldAdded(DurationFieldType.days(), 1).toDate().getTime();
    }


    public boolean dateTimeFromCheck(Date mask, Date value) {
        if (mask == null) return true;
        if (value == null) return false;
        return value.getTime() >= mask.getTime();
    }

    public boolean dateTimeToCheck(Date mask, Date value) {
        if (mask == null) return true;
        if (value == null) return false;
        return value.getTime() <= mask.getTime();
    }

}
