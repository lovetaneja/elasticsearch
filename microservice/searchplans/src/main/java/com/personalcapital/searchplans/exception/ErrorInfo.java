package com.personalcapital.searchplans.exception;

/**
 * @author love_taneja
 * This is a Error Info Object
 */

public class ErrorInfo {

    public final String url;

    public final int errorCode;

    public final String errorMessage;


    /**
     * @param url
     * @param code
     * @param exception
     */
    public ErrorInfo(StringBuffer url, int code, Exception exception) {
        this.url = url.toString();
        this.errorCode = code;
        this.errorMessage = exception.getLocalizedMessage();
    }

}