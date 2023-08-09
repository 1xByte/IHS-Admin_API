package com.adminapi.customExe;

public class NotFoundException extends  RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
