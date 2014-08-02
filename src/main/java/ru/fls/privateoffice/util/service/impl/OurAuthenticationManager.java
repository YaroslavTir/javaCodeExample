package ru.fls.privateoffice.util.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.fls.privateoffice.daoservice.CryptoService;
import ru.fls.privateoffice.daoservice.UserService;
import ru.fls.privateoffice.entity.User;
import ru.fls.privateoffice.service.ClientAuthorizationServiceWrapper;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: NKrivko
 * Date: 26.03.12
 * Time: 12:01
 * To change this template use File | Settings | File Templates.
 */

public class OurAuthenticationManager implements AuthenticationProvider {
    private static final Logger log = Logger.getLogger(OurAuthenticationManager.class);

    @Autowired
    private ClientAuthorizationServiceWrapper clientAuthorizationServiceWrapper;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        Authentication result = null;
        String clientId = null;
        String sessionId = null;
        ClientPrincipalRole role = null;

        String login = auth.getName();
        String password = auth.getCredentials().toString();
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        log.info("Authentication user as admin start ");

        User u = userService.getUser(login);
        if (u != null) {
            role = ClientPrincipalRole.ADMIN;
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
            if (userService.getUser(login, CryptoService.getInstance().encrypt(password)) == null) {
                log.error("Wrong password for user " + login);
                throw new BadCredentialsException("Wrong password for user " + login);
            }

        }

        ClientPrincipal clientPrincipal = new ClientPrincipal(login, sessionId, role, false, false);
        result = new UsernamePasswordAuthenticationToken(clientPrincipal, password, authorities);

        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
