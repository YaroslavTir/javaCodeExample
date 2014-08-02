package ru.fls.privateoffice.util.service.richbanner;

/**
 * @author YMolodkov
 * @since 14.0
 */
public enum AccountStatus {
    ACTIVE("accountStatus.active"),
    BLOCKED("accountStatus.blocked"),
    RESTRICTED("accountStatus.restricted");

    final private String code;

    public String getCode() {
        return code;
    }

    AccountStatus(String code) {
        this.code = code;
    }

}
