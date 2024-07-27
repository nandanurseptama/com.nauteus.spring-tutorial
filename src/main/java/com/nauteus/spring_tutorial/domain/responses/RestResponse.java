package com.nauteus.spring_tutorial.domain.responses;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import java.util.List;

@Setter
@Getter
@Builder
public class RestResponse<T> {
    /*
     * Usually same as http status code
     */
    private String message;

    /*
     * Http status code
     */
    private int status;

    /*
     * Data
     */
    @Nullable
    private T data;

    /*
     * Errors information
     */
    @Nullable
    private Map<String, List<String>> errors;
}
