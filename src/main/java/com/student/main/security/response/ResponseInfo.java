package com.student.main.security.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseInfo {
    private String message;

    public ResponseInfo(String message) {
        this.message = message;
    }

}

