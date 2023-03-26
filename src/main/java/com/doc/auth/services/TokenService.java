package com.doc.auth.services;

import com.doc.auth.exceptions.InvalidTokenException;
import com.doc.auth.models.MarkdownUserModel;

public interface TokenService {

    void validateToken(String jwtToken) throws InvalidTokenException;

    void generateToken(MarkdownUserModel markdownUserModel);
}
