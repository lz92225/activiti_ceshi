package com.demo.exception;

public class AppException extends RuntimeException {

    private String msg;
    private String errcode;

    public AppException(String msg, String errcode) {
        this.msg = msg;
        this.errcode = errcode;
    }
}
