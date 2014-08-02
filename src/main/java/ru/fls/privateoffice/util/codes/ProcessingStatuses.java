package ru.fls.privateoffice.util.codes;

/**
 * User: NKarataeva
 * Date: 04.04.12
 * Time: 12:11
 */
public enum ProcessingStatuses implements CodeEnumWithDescription {
    CONSULTATION_EMPTY_MESSAGE ("-4", "consultation.empty.message.error", "Для отправки сообщения через форму обратной связи необходимо указать текст обращения в поле \"Вопрос\"."),
    WRONG_OLD_PASSWORD ("-3", "wrong.old.password.error", "Неверно введен старый пароль"),
    FEEDBACK_MESSAGE_TIMEOUT_ERROR ("-2", "feedback.message.timeout.error", "Отправка сообщений доступна не чаще одного раза в 10 минут"),
    UNKNOWN_ERROR ("-1", "unknown.error", "Произошла ошибка"),
    SUCCESS ("1", "success", "Операция выполнена успешно"),
    CLIENT_ALREADY_EXISTS_ERROR("2", "client.already.exists.error", "Участник уже зарегистрирован в системе"),
    CLIENT_DOES_NOT_EXISTS_ERROR("3", "client.does.not.exist.error", "Участник не найден"),
    TIMEOUT_ERROR ("4", "timeout.error", "Время ожидания истекло"),
    REGISTRATION_ALREADY_CONFIRMED_ERROR ("5", "registration.already.confirmed.error", "Регистрация уже подтверждена"),
    VALIDATION_ERROR ("6", "data.validation.error", "Некорректные данные"),
    DICTIONARY_NOT_FOUND_ERROR ("7", "dictionary.not.found.error", "Системные данные не найдены"),
    INTERNAL_ERROR ("8", "internal.error", "Внутренняя ошибка"),
    CLIENT_ALREADY_AUTHORIZED_ERROR ("9", "client.already.authorized.error", "Участник уже авторизован"),
    INVALID_PASSWORD_ERROR ("10", "invalid.password.error", "Неправильная пара логин-пароль! Авторизоваться не удалось."),
    ACCOUNT_DOES_NOT_EXISTS_ERROR ("11", "account.does.not.exist.error", "Участник с таким логином не зарегистрирован."),
    REGISTRATION_NOT_CONFIRMED_ERROR ("12", "registration.not.confirmed.yet.error", "Регистрация не подтверждена"),
    MIDDLE_NAME_ALREADY_SET_ERROR ("13", "middle.name.already.set.error", "Отчество уже было указано"),
    INVALID_SECURITY_ANSWER_ERROR("14", "invalid.security.answer.error", "Неправильная пара вопрос-ответ. Восстановить доступ не удалось."),
    CLIENT_NOT_AUTHORIZED_ERROR ("15", "client.not.authorized.error", "Пользователь не авторизован"),
    JOURNEY_NO_DATA_ERROR ("16", "no.data.about.the.journey.error", "Данные о поездке не найдены"),
    NULL_REQUEST_ERROR ("17", "null.request.retrieved.error", "Получен пустой запрос"),
    REGISTRATION_CAN_NOT_BE_CONFIRMED ("18", "registration.can.not.be.confirmed", "С момента регистрации прошло больше чем 24 часа. Пройдите регистрацию повторно."),
    ACCESS_DENIED_ERROR ("19", "access.denied", "Доступ запрещен"),
    ACCOUNT_IS_VALID ("20", "account.is.valid", "Счет корректен"),
    SECURITY_QUESTION_IS_VALID ("21", "security.question.is.valid", "Секретный вопрос корректен"),
    SECURITY_ANSWER_IS_VALID ("22", "security.answer.is.valid", "Секретный ответ корректен"),
    INVALID_SECURITY_QUESTION_ERROR ("23", "invalid.security.question.error", "Неправильная пара вопрос-ответ. Восстановить доступ не удалось."),
    THIRTY_DAYS_PERIOD_VIOLATION_ERROR ("24", "thirty.days.period.violation.error", "Со дня поездки не прошло еще 30 дней"),
    HUNDRED_AND_EIGHTY_PERIOD_VIOLATION_ERROR ("25", "hundred.and.eighty.period.violation.error", "Со дня поездки прошло более 180 дней"),
    LOYALTY_PROGRAM_BEGIN_DATE_PERIOD_VIOLATION_ERROR ("26", "loyalty.program.begin.date.period.violation.error", "Программа лояльности была запущена позже указанной даты"),
    REGISTRATION_DATE_PERIOD_VIOLATION_ERROR ("27", "registration.date.period.violation.error", "Поездка была совершена более, чем за 30 дней до регистрации в Программе лояльности"),
    RETRO_TICKET_ALREADY_EXISTS_ERROR ("28" ,"retro.ticket.already.exists.error", "В системе уже существует поездка с указанным билетом"),
    TRIP_ALREADY_EXISTS_ERROR ("29", "trip.already.exists.error", "Баллы за поездку уже начислены"),
    RETRO_ALREADY_EXISTS_ERROR ("30", "retro.already.exists.error", "Поездка уже внесена"),
    NO_DOCUMENTS_ATTACHED_ERROR ("31", "no.documents.attached.error", "Необходимо указать хотя бы один документ"),
    CONSULTATION_DOES_NOT_EXISTS_ERROR ("32", "consultation.does.not.exist.error", "Консультация не найдена"),
    FILE_NOT_FOUND_ERROR ("33", "file.not.found.error", "Файл не найден или недостаточно прав для редактирования"),    
    LANG_ALREADY_EXISTS_ERROR("34", "multilanguage.add.duplicate", "Такой язык уже существует"),
    REGISTRATION_ACTIVATION_EMAIL_ERROR ("35", "registration.activation.email.error", "По техническим причинам письмо с логином и ссылкой для активации учетной записи не может быть отправлено на указанный Вами адрес. " +
            "Приносим свои извинения и просим сообщить о проблеме в программу через форму обратной связи на сайте."),
    INVALID_PHONE_NUMBER_ERROR ("41", "invalid.phone.number.error", "Указан неверный номер телефона. Восстановить доступ не удалось.");

    private String clientDescription;
    private String statusDescription;
    private String code;

    /**
     * Constructor.
     */
    private ProcessingStatuses(String code, String status, String clientDescription) {
        this.statusDescription = status;
        this.code = code;
        this.clientDescription = clientDescription;
    }

    public String getStatusDescription() {
        return this.statusDescription;
    }

    @Override
    public String getCode() {
        return code;
    }

    public String getClientDescription() {
        return clientDescription;
    }

    public static ProcessingStatuses getConstant(String code) throws Exception{
        return EnumHelper.parseEnumCode(ProcessingStatuses.class, code);
    }
}
