package com.bah.service.exception;

@SuppressWarnings("serial")
public class ClientNameAlreadyExistsException extends ClientServiceException {

    public ClientNameAlreadyExistsException() {
        super("There is already a client with the specified name");
    }
}