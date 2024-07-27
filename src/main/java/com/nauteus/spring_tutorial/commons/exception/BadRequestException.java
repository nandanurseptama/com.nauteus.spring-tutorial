package com.nauteus.spring_tutorial.commons.exception;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Builder
@Getter
public class BadRequestException extends RuntimeException {

    private Map<String, List<String>> errors;

    public BadRequestException setError(@NonNull String field, @NonNull String message) {
        if (errors == null) {
            errors = new HashMap<String, List<String>>(0);

            final List<String> messages = new ArrayList<String>(0);

            messages.add(message);

            errors.put(field, messages);
            return BadRequestException.builder().errors(errors).build();
        }
        List<String> fieldErrors = errors.get(field);
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<String>(0);
            fieldErrors.add(message);
            errors.put(field, fieldErrors);
            return BadRequestException.builder().errors(errors).build();
        }

        fieldErrors.add(message);
        errors.put(field, fieldErrors);
        return BadRequestException.builder().errors(errors).build();

    }
}