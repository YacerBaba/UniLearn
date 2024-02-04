package com.yacer.unilearn.auth.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Message {
    private HttpStatus status;
    private String message;
}
