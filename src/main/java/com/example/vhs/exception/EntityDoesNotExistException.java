package com.example.vhs.exception;

public class EntityDoesNotExistException extends RuntimeException{
    public EntityDoesNotExistException(){
        super("Entity does not exist");
    }
    public EntityDoesNotExistException(String message){
        super(message);
    }
}
