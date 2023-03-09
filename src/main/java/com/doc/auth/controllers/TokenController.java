package com.doc.auth.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/token")
public class TokenController {

    /*
    This will not be called by a frontend, but by an api gateway in order to
    check for request if it`s legit or not
    */
    @GetMapping("/validate")
    public void validateToken(HttpServletRequest httpServletRequest) throws Exception {
        //throw  new Exception("Some random exception");
    }
}
