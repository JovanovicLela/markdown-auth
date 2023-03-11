package com.doc.auth.controllers;

import com.doc.auth.services.TokenService;
import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenService tokenService;

    /*
    This will not be called by a frontend, but by an api gateway in order to
    check for request if it`s legit or not
    */
    @GetMapping("/validate")
    public void validateToken(HttpServletRequest httpServletRequest) throws Exception {

        String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(authHeader);

        String token = null;
        if (!isEmpty(authHeader)) {

            token = authHeader.split("\\s")[1];
            System.out.println(token);

        }

        //String jwtToken = "";
        tokenService.validateToken(token);
    }
}
