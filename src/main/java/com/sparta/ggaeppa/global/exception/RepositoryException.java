package com.sparta.ggaeppa.global.exception;

public class RepositoryException extends DefaultException {
    public RepositoryException(ExceptionStatus exceptionStatus) {
        super(exceptionStatus);
    }
}
