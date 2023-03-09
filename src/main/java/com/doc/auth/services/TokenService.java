package com.doc.auth.services;

public interface TokenService {

    void validateToken(String jwtToken);
}
