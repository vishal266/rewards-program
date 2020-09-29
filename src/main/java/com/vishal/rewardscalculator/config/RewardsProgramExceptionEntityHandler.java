package com.vishal.rewardscalculator.config;

import com.vishal.rewardscalculator.exception.RewardsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class to handle rewards exception
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RewardsProgramExceptionEntityHandler extends ResponseEntityExceptionHandler {

    Logger log = LoggerFactory.getLogger(RewardsProgramExceptionEntityHandler.class);

    @ExceptionHandler(RewardsException.class)
    public final ResponseEntity<Object> handleRewardsProgramException(RewardsException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        log.error(bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
