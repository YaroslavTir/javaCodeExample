package ru.fls.privateoffice.util.codes;

import java.util.logging.Logger;

/**
 * User: NKarataeva
 * Date: 04.04.12
 * Time: 12:36
 */
public class EnumHelper {
    public static <T extends CodeEnum> T parseEnumCode(Class<T> enumClass, String code) throws Exception {
        if (code != null) {
            for (T type : enumClass.getEnumConstants()) {
                if (type.getCode().equals(code)) {
                    return type;
                }
            }
        }
        String msg = "Cannot parse " + enumClass + " by code: '" + code + "'";
        throw new Exception(msg);
    }
}
