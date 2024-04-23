package com.ebay.test.managersystem.exception;

import com.ebay.test.managersystem.common.Result;
import com.ebay.test.managersystem.common.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@ResponseBody
public class RestExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<String> authorizationException(AuthorizationException e) {
        LOGGER.debug("AuthorizationException: ", e);
        return Result.fail(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<String> noHandlerFoundException(NoHandlerFoundException e) {
        LOGGER.debug("NoHandlerFoundException: ", e);
        return Result.fail(ReturnCode.RC404);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> nullPointerException(NullPointerException e) {
        LOGGER.error("NullPointerException: ", e);
        return Result.fail(ReturnCode.RC400);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result<String> httpMediaTypeNotSupportedException(HttpRequestMethodNotSupportedException e) {
        LOGGER.debug("HttpRequestMethodNotSupportedException: ", e);
        return Result.fail(ReturnCode.RC405);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception(Exception e) {
        LOGGER.error("Unmatched Exception: ", e);
        return Result.fail(ReturnCode.RC500);
    }
}
