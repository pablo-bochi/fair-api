package com.bochi.fairapi.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FieldError {

    private String field;
    private List<String> errors;

}
