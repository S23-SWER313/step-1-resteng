package com.resteng.resteng.Exception;



public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Could not find " + id);
    }
}
