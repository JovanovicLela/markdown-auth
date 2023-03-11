package com.doc.auth.exceptions.handler;

import com.mongodb.MongoWriteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MongoWriteException.class})
    public ResponseEntity<Object> handlerMongoWriteException(final MongoWriteException e, final WebRequest request) {

        String bodyResponseString = "Username, email or displayName already exist";

        log.error(bodyResponseString);
        return handleExceptionInternal(e, bodyResponseString, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
