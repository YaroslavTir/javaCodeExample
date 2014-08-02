package ru.fls.privateoffice.util.service.impl;

import ru.fls.privateoffice.util.codes.CodeEnum;
import ru.fls.privateoffice.util.codes.EnumHelper;

import java.io.Serializable;

/**
 * User: NKarataeva
 * Date: 13.04.12
 * Time: 11:13
 */
public enum ClientPrincipalRole implements CodeEnum, Serializable {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private static final long serialVersionUID = 1L;

    private String role;

    /**
     * Constructor.
     */
    private ClientPrincipalRole(String role) {
        this.role = role;
    }

    @Override
    public String getCode() {
        return role;
    }

    public static ClientPrincipalRole getConstant(String code) throws Exception {
        return EnumHelper.parseEnumCode(ClientPrincipalRole.class, code);
    }
}
