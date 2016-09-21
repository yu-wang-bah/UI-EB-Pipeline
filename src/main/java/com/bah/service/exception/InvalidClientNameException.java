package com.bah.service.exception;

public class InvalidClientNameException extends ClientServiceException {

    public InvalidClientNameException() {
        super("An invalid client name was specified");
    }
}