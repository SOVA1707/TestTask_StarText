package com.startext.testtask.exception;

public class CommentNotFoundException extends Exception{
    public CommentNotFoundException() {
        super("Comment not found.");
    }
}
