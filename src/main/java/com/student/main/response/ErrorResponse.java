package com.student.main.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse<T> {

    private T data;
    private Integer statusCode;
    private String error;

}
