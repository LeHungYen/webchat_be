package com.webchat.webchat_be.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.ErrorResponse;

@Data
@AllArgsConstructor
public class UserException {
    private String message;
}
