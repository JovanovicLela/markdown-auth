package com.doc.auth.config.security;

import com.doc.auth.daos.UserDAO;
import com.doc.auth.exceptions.InvalidTokenException;
import com.doc.auth.exceptions.MarkdownTokenAuthException;
import com.doc.auth.models.MarkdownUserModel;
import com.doc.auth.services.TokenService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class MarkdownAuthProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    UserDAO userDAO;

    @Autowired
    TokenService tokenService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @SneakyThrows
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        final String token = (String) authentication.getCredentials();

        if (isEmpty(token)) {
            return new User(username, "", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
        }

        Optional<MarkdownUserModel> markdownUserModelOptional = userDAO.findByJwtToken(token);

        if (markdownUserModelOptional.isPresent()) {

            final MarkdownUserModel markdownUserModel = markdownUserModelOptional.get();
            try {
                tokenService.validateToken(token);
            } catch (InvalidTokenException e) {
                markdownUserModel.setJwtToken(null);
                userDAO.save(markdownUserModel);

                return null;
            }

            return new User(username, "",
                    AuthorityUtils.createAuthorityList(
                    markdownUserModel.getRoles().stream().map(roleName -> "ROLE_" + roleName).toArray(String[]::new)));
        }

        throw new MarkdownTokenAuthException("User not found for token " + token);
    }
}
