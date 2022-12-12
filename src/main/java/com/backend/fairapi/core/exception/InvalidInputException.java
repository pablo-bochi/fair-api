package com.backend.fairapi.core.exception;

import com.backend.fairapi.helper.Utils;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
@NoArgsConstructor
@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message)  {
        super(message);
    }

    public InvalidInputException(List<FieldError> fieldErrors) {
        super(Utils.toJson(fieldErrors));
    }

}
