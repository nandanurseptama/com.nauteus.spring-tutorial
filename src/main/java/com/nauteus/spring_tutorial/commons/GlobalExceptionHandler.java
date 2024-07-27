package com.nauteus.spring_tutorial.commons;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nauteus.spring_tutorial.commons.exception.BadRequestException;
import com.nauteus.spring_tutorial.domain.responses.RestResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public RestResponse<String> constraintViolationException(ConstraintViolationException e) {
        final Map<String, List<String>> errors = new HashMap<String, List<String>>(0);
        final Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            final String field = getFieldFromPath(violation.getPropertyPath());
            List<String> errorMessages = errors.get(field);

            if (errorMessages == null) {
                errorMessages = new ArrayList<String>(0);
                errorMessages.add(violation.getMessage());
            } else {
                errorMessages.add(violation.getMessage());
            }

            if (errorMessages.size() > 0) {
                errors.put(field, errorMessages);
            }
        }
        return RestResponse.<String>builder().message(HttpStatus.BAD_REQUEST.getReasonPhrase()).data(null).status(400)
                .errors(errors).build();
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public RestResponse<String> unsupportedMediaTypeException(HttpMediaTypeNotSupportedException e) {
        final Map<String, List<String>> errors = new HashMap<String, List<String>>(0);
        final List<String> errorMessages = new ArrayList<String>(0);
        errorMessages.add(e.getMessage());

        errors.put("body", errorMessages);
        return RestResponse.<String>builder().message(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase()).data(null)
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .errors(errors).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    public RestResponse<String> serviceException(BadRequestException e) {
        return RestResponse.<String>builder().message(HttpStatus.BAD_REQUEST.getReasonPhrase()).data(null)
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(e.getErrors()).build();
    }

    private String getFieldFromPath(Path fieldPath) {

        PathImpl pathImpl = (PathImpl) fieldPath;
        return pathImpl.getLeafNode().toString();

    }
}
