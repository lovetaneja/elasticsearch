package com.personalcapital.searchplans.exception;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;

/**
 * @author love_taneja
 * This is a Global Exception Handler
 */
@ControllerAdvice(annotations = Controller.class)
class GlobalExceptionHandler {

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
    	logger.error("ApiException Occurred " , apiException.getMessage(), apiException);
   		return new ErrorInfo(httpServletRequest.getRequestURL(), apiException.getCode(), new Exception(apiException.getMessage()));
    }



    /**
     * @param exception
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception exception){
    	logger.error("Exception Occurred ",exception.getMessage(), exception);
        String errorMessage = exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}