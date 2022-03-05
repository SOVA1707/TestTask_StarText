package com.startext.testtask.exception;

public class CommentAlreadyExistException extends Exception{
    public CommentAlreadyExistException() {
        super("Comment by this id already exist.");
    }
}
