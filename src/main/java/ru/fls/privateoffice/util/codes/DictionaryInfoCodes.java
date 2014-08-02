package ru.fls.privateoffice.util.codes;

/**
 * User: NKarataeva
 * Date: 23.04.12
 * Time: 12:02
 */
public enum DictionaryInfoCodes implements CodeEnum {
    ACCOUNT_LEVEL("account_level", "Уровень участника"),
    ACCOUNT_ACCESS("account_access", "Тип доступа к счету"),
    OPERATION("operation", "Операции"),
    DOCUMENT_TYPE("document_type", "Тип документов"),
    LEVEL_CHANGE_REASON("level_change_reason", "Причина изменения уровня"),
    LINK("link", "Связь"),
    REASON_CHARGE("reason_charge", "Причина(Начисление)"),
    REASON_CHARGE_OFF("reason_charge_off", "Причина(Списание)"),
    SCORING_RECKONING("scoring_reckoning", "Способ расчета баллов"),
    SCORING_SCHEME_TYPE("scoring_scheme_type", "Тип схемы"),
    PURCHASE("purchase", "Способ покупки"),
    CITIZENSHIP("citizenships", "Гражданство "),
    PARTNERS("partners", "Аналогичные программы других транспортных компаний"),
    SECURITY_QUESTION("security_question", "Контрольный вопрос"),
    LOCALITY_TYPE("locality_type", "Тип населенного пункта"),
    STREETS_TYPE("streets_type", "Тип улицы"),
    STATIONS("stations", "Справочник станций"),
    SUPER_STATIONS("super_stations", "Справочник супер-станций"),
    ACCOUNT_HISTORY_OPERATION("acc_hist_operation", "Операции для истории счета"),
    TICKET_TYPE("ticket_type", "АСУ Экспресс: Тип железнодорожного билета"),
    TRAIN_TYPE("train_type", "АСУ Экспресс: Тип поезда"),
    CARRIAGE("carriage", "АСУ Экспресс: Тип вагона"),
    COMFORT("comfort", "АСУ Экспресс: Признак вагона повышенной комфортности"),
    ADD_SERVICE("add_service", "АСУ Экспресс: Признак фирменности вагона"),
    TICKET_CATEGORY("ticket_category", "АСУ Экспресс: Вид проездного документа"),
    SALE_AGENT("sale_agent", "АСУ Экспресс: Агенты продажи"),
    PAYMENT("payment", "АСУ Экспресс: Виды расчета"),
    PARTNER_TYPE("partnerType", "Тип партнёра"),
    SERVICE_CLASS("service_class", "АСУ Экспресс: Класс обслуживания"),
    POSITION_CATEGORY("position_category", "Категория должности"),
    CONSULTATION_CATEGORY("consult_category", "Категория вопроса"),
    CONSULTATION_GUEST_CATEGORY("cons_category_guest", "Категория вопроса для незарегистрированных пользователей"),
    INFO_SOURCE("info_source", "Откуда узнали о программе"),
    ADDITION_STATUS("additionStatus", "Дополнительный статус"),
    CARD_REPRINT_REASON("card_reprint_reason", "Причина запроса на выпуск дубликата карты"),

    LANGUAGE("language", "Справочник языков")
    ;

    private String description;
    private String code;

    /**
     * Constructor.
     */
    private DictionaryInfoCodes(String code, String description) {
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

    public static DictionaryInfoCodes getConstant(String code) throws Exception {
        return EnumHelper.parseEnumCode(DictionaryInfoCodes.class, code);
    }
}
