package com.doc.auth.services.impl;

import com.doc.auth.models.MarkdownUserModel;
import com.doc.auth.services.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public void validateToken(String jwtToken) {

    }

    @Override
    public void generateToken(MarkdownUserModel markdownUserModel) {
        // for test
        markdownUserModel.setJwtToken("randomstring");
    }
}
