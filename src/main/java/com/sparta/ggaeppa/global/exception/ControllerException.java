package com.sparta.ggaeppa.global.exception;

public class ControllerException extends DefaultException {
    public ControllerException(ExceptionStatus exceptionStatus) {
        super(exceptionStatus);
    }
}
