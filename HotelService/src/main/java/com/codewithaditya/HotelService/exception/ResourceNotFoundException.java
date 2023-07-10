package com.codewithaditya.HotelService.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("HOTEL NOT FOUND IN SERVER !!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
