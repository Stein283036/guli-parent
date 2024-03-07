package org.stein.handler.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.stein.result.Result;

/**
 * @author stein
 * @date 2024/3/6
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GuliException.class)
    public Result guliExceptionHandler(GuliException e) {
        log.error(e.getMessage());
        return Result.error().code(e.getCode()).message(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.error().message(e.getMessage());
    }
}
