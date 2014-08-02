package ru.fls.privateoffice.util.service.impl;

import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * User: NKarataeva
 * Date: 26.06.12
 * Time: 12:57
 */
public class OurAuthenticationProcessingFilterEntryPoint extends LoginUrlAuthenticationEntryPoint {
    public void setLoginFormUrl(String loginFormUrl) {
        super.setLoginFormUrl(loginFormUrl);
    }
}
