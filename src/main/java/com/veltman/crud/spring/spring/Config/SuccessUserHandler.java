package com.veltman.crud.spring.spring.Config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin");
        } else if (roles.contains("ROLE_BUH")) {
            response.sendRedirect("/buh");
        } else if (roles.contains("ROLE_OWNER")) {
            response.sendRedirect("/owner");
        } else if (roles.contains("ROLE_EXECUTIVE_DIRECTOR")) {
            response.sendRedirect("/executiveDirector");
        } else if (roles.contains("ROLE_HR")) {
            response.sendRedirect("/hr");
        } else if (roles.contains("ROLE_USER")) {
            response.sendRedirect("/user");
        }
    }

}