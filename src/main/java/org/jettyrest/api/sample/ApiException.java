package org.jettyrest.api.sample;

public class ApiException extends RuntimeException {

    private int errorCode;

    public ApiException(String s, Throwable throwable, int errorCode) {
        super(s, throwable);
        this.errorCode = errorCode;
    }

    public ApiException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}