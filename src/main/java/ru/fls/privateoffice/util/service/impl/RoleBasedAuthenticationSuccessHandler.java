package ru.fls.privateoffice.util.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * User: NKarataeva
 * Date: 28.05.12
 * Time: 16:46
 */
public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private Map<String, String> roleUrlMap;

    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        if (authentication.getPrincipal() instanceof ClientPrincipal) {
            ClientPrincipalRole role = ((ClientPrincipal) authentication.getPrincipal()).getRole();
            String url = role != null ? roleUrlMap.get(role.getCode()) : "/adminlogin.html?loginerror";

            response.sendRedirect(request.getContextPath() + url);
        }
    }

    public void setRoleUrlMap(Map<String, String> roleUrlMap) {
        this.roleUrlMap = roleUrlMap;
    }
}
