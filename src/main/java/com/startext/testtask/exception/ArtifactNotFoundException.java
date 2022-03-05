package com.startext.testtask.exception;

public class ArtifactNotFoundException extends Exception{
    public ArtifactNotFoundException() {
        super("Artifact not found.");
    }
}
