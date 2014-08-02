package ru.fls.privateoffice.util.builder;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import ru.fls.privateoffice.util.service.impl.ClientPrincipal;

/**
 * User: NKarataeva
 * Date: 23.04.12
 * Time: 13:18
 */
public abstract class AuthorizedPageBuilder implements AuthorizedBuilder {
	
    @Override
    public String getSessionId() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null && securityContext.getAuthentication() != null) {
            if (securityContext.getAuthentication().getPrincipal() instanceof ClientPrincipal) {
                ClientPrincipal principal = (ClientPrincipal) securityContext.getAuthentication().getPrincipal();
                return principal.getJSessionId();
            }
        }
        return null;
    }
    
    
}
