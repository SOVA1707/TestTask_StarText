package com.startext.testtask.exception;

public class ArtifactAlreadyExistException extends Exception{
    public ArtifactAlreadyExistException() {
        super("Artifact by this id already exist.");
    }
}
