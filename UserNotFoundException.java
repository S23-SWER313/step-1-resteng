package com.osama.rest;

public class UserNotFoundException extends RuntimeException{
    UserNotFoundException(Integer id) {
        super("Could not find employee " + id);
      }

}
