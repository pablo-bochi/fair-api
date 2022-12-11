package com.bochi.fairapi.core.exception;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@NoArgsConstructor
public class FailedToLoadFileException extends RuntimeException {

    public FailedToLoadFileException(String message)  {
        super(message);
    }

}
