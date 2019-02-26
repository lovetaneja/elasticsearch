package com.personalcapital.searchplans.exception;

/**
 * @author love_taneja
 * This is exception class for any API exception
 */
public class ApiException extends Exception {

    private int code;

    /**
     * @param code
     * @param msg
     */
    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    /**
     * @return int
     */
    public int getCode() {
        return this.code;
    }
}