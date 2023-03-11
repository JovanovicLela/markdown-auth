package com.doc.auth.exceptions;

import javax.naming.AuthenticationException;

public class MarkdownTokenAuthException extends AuthenticationException {
    public MarkdownTokenAuthException(String s) {
        super(s);
    }
}
