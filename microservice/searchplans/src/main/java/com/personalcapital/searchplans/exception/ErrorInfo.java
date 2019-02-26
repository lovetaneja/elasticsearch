package com.personalcapital.searchplans.exception;

/**
 * @author love_taneja
 * This is a Error Info Object
 */

public class ErrorInfo {

    public final String url;

    public final int errorCode;

    public final String errorMessage;


    public ErrorInfo(StringBuffer url, int code, Exception ex) {
        this.url = url.toString();
        this.errorCode = code;
        this.errorMessage = ex.getLocalizedMessage();
    }

}