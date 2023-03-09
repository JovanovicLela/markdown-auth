package com.doc.auth.services;

public interface TokenService {

    void validate(String jwtToken);
}
