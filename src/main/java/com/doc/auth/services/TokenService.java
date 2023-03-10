package com.doc.auth.services;

import com.doc.auth.models.MarkdownUserModel;

public interface TokenService {

    void validateToken(String jwtToken);

    void generateToken(MarkdownUserModel markdownUserModel);
}
