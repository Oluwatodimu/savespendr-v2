package com.savespendr.backend.user_management_service.exception;

public class KeycloakException extends RuntimeException {

    public KeycloakException(String message) {
        super(message);
    }
}
