package com.linktic.stock.infrastructure.controller;

import com.linktic.stock.infrastructure.controller.dto.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<MessageResponse> handleUnknownException(Exception e) {
        log.error(e.getMessage(), e);
        var messageResponse = MessageResponse.builder()
                .withMessage(e.getMessage())
                .withCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return ResponseEntity.of(Optional.of(messageResponse));
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<MessageResponse> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        var messageResponse = MessageResponse.builder()
                .withMessage(e.getMessage())
                .withCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.of(Optional.of(messageResponse));
    }
}
