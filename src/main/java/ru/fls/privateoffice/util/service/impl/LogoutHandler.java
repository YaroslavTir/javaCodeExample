package ru.fls.privateoffice.util.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import ru.fls.privateoffice.service.LogoutServiceWrapper;
import ru.rzd.loyalty.api.security.LogoutFault;
import ru.rzd.loyalty.api.security.LogoutResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: NKrivko
 * Date: 16.04.12
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public class LogoutHandler implements LogoutSuccessHandler {

    private static final Logger log = Logger.getLogger(LogoutHandler.class);

    @Autowired
    private LogoutServiceWrapper logoutServiceWrapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ClientPrincipal principal = (ClientPrincipal) authentication.getPrincipal();

        switch (principal.getRole()) {
            case USER:
                try {
                    String loyaltySession = principal.getJSessionId();
                    LogoutResponse resp = logoutServiceWrapper.logout(loyaltySession);
                    httpServletResponse.sendRedirect("login.html");
                } catch (LogoutFault logoutFault) {
                    log.error("Logout service error: code=" + logoutFault.getFaultInfo().getCode() + ", description=" + logoutFault.getFaultInfo().getMessage());
                    return;
                }
                break;

            case ADMIN:
            default:
                httpServletResponse.sendRedirect("login.html");
                break;
        }

        log.info("Logout successful: login=" + principal.getName() + ", role=" + principal.getRole());

    }
}
