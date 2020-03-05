package com.pdf.convert;

public class myException extends Exception {
    public myException() {
        super();
    }
    public myException(String message) {
        super(message);
    }
    public myException(String message, Throwable cause) {
        super(message, cause);
    }
    public myException(Throwable cause) {
        super(cause);
    }
}
