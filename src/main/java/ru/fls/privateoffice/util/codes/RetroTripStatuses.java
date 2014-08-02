package ru.fls.privateoffice.util.codes;

/**
 * User: NKarataeva
 * Date: 17.10.12
 * Time: 15:46
 */
public enum RetroTripStatuses implements CodeEnum{
    CONFIRMED("confirmed"),
    REJECTED("rejected"),
    RUN("run"),
    IN_PROGRESS("in progress");

    private String code;

    private RetroTripStatuses(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;    }

    public static RetroTripStatuses getConstant(String code) throws Exception{
        return EnumHelper.parseEnumCode(RetroTripStatuses.class, code);
    }
}
