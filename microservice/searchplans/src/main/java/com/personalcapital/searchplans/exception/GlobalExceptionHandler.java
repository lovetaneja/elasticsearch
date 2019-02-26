package com.personalcapital.searchplans.exception;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author love_taneja
 * This is a Global Exception Handler
 */
@ControllerAdvice(annotations = Controller.class)
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    /**
     * @param httpServletRequest
     * @param apiException
     * @return ErrorInfo
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ErrorInfo handleException(HttpServletRequest httpServletRequest, ApiException apiException) {
        logger.error("ApiException Occurred ", apiException.getMessage(), apiException);
        return new ErrorInfo(httpServletRequest.getRequestURL(), apiException.getCode(), new Exception(apiException.getMessage()));
    }

    /**
     * @param httpServletRequest
     * @param clientProtocolException
     * @return ErrorInfo
     */
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ClientProtocolException.class)
    @ResponseBody
    public ErrorInfo handleException(HttpServletRequest httpServletRequest, ClientProtocolException clientProtocolException) {
        logger.error("ClientProtocolException Occurred ", clientProtocolException.getMessage(), clientProtocolException);
        return new ErrorInfo(httpServletRequest.getRequestURL(), ErrorCodes.REST_CALL_CLIENT_PROTOCOL_EXCEPTION_CODE, new Exception(clientProtocolException.getMessage()));
    }

    /**
     * @param httpServletRequest
     * @param ioException
     * @return ErrorInfo
     */
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public ErrorInfo handleException(HttpServletRequest httpServletRequest, IOException ioException) {
        logger.error("IOException Occurred ", ioException.getMessage(), ioException);
        return new ErrorInfo(httpServletRequest.getRequestURL(), ErrorCodes.REST_CALL_IO_EXCEPTION_CODE, new Exception(ioException.getMessage()));
    }

    /**
     * @param httpServletRequest
     * @param jsonSyntaxException
     * @return ErrorInfo
     */
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(JsonSyntaxException.class)
    @ResponseBody
    public ErrorInfo handleException(HttpServletRequest httpServletRequest, JsonSyntaxException jsonSyntaxException) {
        logger.error("JsonSyntaxException Occurred ", jsonSyntaxException.getMessage(), jsonSyntaxException);
        return new ErrorInfo(httpServletRequest.getRequestURL(), ErrorCodes.PARSE_RESPONSE_JSON_SYNTAX_EXCEPTION_CODE, new Exception(jsonSyntaxException.getMessage()));
    }

    /**
     * @param httpServletRequest
     * @param jsonIOException
     * @return ErrorInfo
     */
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(JsonIOException.class)
    @ResponseBody
    public ErrorInfo handleException(HttpServletRequest httpServletRequest, JsonIOException jsonIOException) {
        logger.error("JsonIOException Occurred ", jsonIOException.getMessage(), jsonIOException);
        return new ErrorInfo(httpServletRequest.getRequestURL(), ErrorCodes.PARSE_RESPONSE_JSON_IO_EXCEPTION_CODE, new Exception(jsonIOException.getMessage()));
    }

    /**
     * @param httpServletRequest
     * @param jsonParseException
     * @return ErrorInfo
     */
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(JsonParseException.class)
    @ResponseBody
    public ErrorInfo handleException(HttpServletRequest httpServletRequest, JsonParseException jsonParseException) {
        logger.error("JsonParseException Occurred ", jsonParseException.getMessage(), jsonParseException);
        return new ErrorInfo(httpServletRequest.getRequestURL(), ErrorCodes.PARSE_RESPONSE_JSON_PARSE_EXCEPTION_CODE, new Exception(jsonParseException.getMessage()));
    }

    /**
     * @param httpServletRequest
     * @param exception
     * @return ErrorInfo
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo handleException(HttpServletRequest httpServletRequest, Exception exception) {
        logger.error("Exception Occurred ", exception.getMessage(), exception);
        return new ErrorInfo(httpServletRequest.getRequestURL(), ErrorCodes.REST_CALL_EXCEPTION_CODE, new Exception(exception.getMessage()));
    }
}