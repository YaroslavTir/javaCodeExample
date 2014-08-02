package ru.fls.privateoffice.util.codes;

/**
 * User: NKarataeva
 * Date: 05.05.12
 * Time: 15:37
 */
@Deprecated
public enum PositionCategoryDictionaryInfoCodes implements CodeEnum {
    MANAGER("manager", "Руководитель"),
    ASSISTANT("assistant", "Помощник (советник)"),
    SPECIALIST("specialist", "Cпециалист"),
    PROVIDING_SPECIALIST("providing specialist", "Обеспечивающий специалист");

    private String description;
    private String code;

    /**
     * Constructor.
     */
    private PositionCategoryDictionaryInfoCodes(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getCode() {
        return code;
    }

    public static PositionCategoryDictionaryInfoCodes getConstant(String code) throws Exception {
        return EnumHelper.parseEnumCode(PositionCategoryDictionaryInfoCodes.class, code);
    }
}
