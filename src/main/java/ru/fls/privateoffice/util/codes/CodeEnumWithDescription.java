package ru.fls.privateoffice.util.codes;

/**
 * User: PGrebenyuk
 * Date: 14.12.2013
 * Time: 13:04
 */
public interface CodeEnumWithDescription extends CodeEnum{   
    /**
     * @return description message code
     */
    public String getStatusDescription();

}
