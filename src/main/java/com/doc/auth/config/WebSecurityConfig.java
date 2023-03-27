package com.doc.auth.config;

import com.doc.auth.config.security.AuthFilter;
import com.doc.auth.config.security.MarkdownAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MarkdownAuthProvider markdownAuthProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authenticationProvider(markdownAuthProvider)
                .addFilterBefore(authFilter(), AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .httpBasic().disable()
                .logout().disable()
                .cors();
    }

    public AuthFilter authFilter() throws Exception {

        OrRequestMatcher orRequestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher("/user/**"),
                new AntPathRequestMatcher("/token/**"),
                new AntPathRequestMatcher("/role/**"),
                new AntPathRequestMatcher( "/swagger-resources"),
                new AntPathRequestMatcher( "/swagger-resources/**"),
                new AntPathRequestMatcher( "/configuration/ui"),
                new AntPathRequestMatcher("/configuration/security"),
                new AntPathRequestMatcher( "/swagger-ui.html"),
                new AntPathRequestMatcher( "/webjars/**" ),
                new AntPathRequestMatcher("/v3/api-docs/**" ),
                new AntPathRequestMatcher("/api/public/**" ),
                new AntPathRequestMatcher("/api/public/authenticate"),
                new AntPathRequestMatcher( "/actuator/*"),
                new AntPathRequestMatcher( "/swagger-ui/**" ),
                new AntPathRequestMatcher( "/swagger-ui-custom.html" )

        );
        AuthFilter authFilter = new AuthFilter(orRequestMatcher);
        authFilter.setAuthenticationManager(authenticationManager());
        return authFilter;

    }

}
