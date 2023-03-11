package com.doc.auth.config.security;

import com.google.common.net.HttpHeaders;
import org.bson.json.JsonObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.ObjectUtils.isEmpty;

public class AuthFilter extends AbstractAuthenticationProcessingFilter {

    public AuthFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public AuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {


        String tokenUnstripped= request.getHeader(HttpHeaders.AUTHORIZATION);

        String token;
        token = tokenUnstripped.split("\\s")[1];

        Authentication authentication;
        if (isEmpty(token)) {
            authentication = new UsernamePasswordAuthenticationToken("guest", "");
        } else {
            authentication = new UsernamePasswordAuthenticationToken("user", token);
        }

        return getAuthenticationManager().authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        JsonObject jsonObject = new JsonObject("error");

        response.getWriter().print(jsonObject);
        response.getWriter().flush();
    }
}
