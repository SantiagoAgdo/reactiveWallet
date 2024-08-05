package com.santiagoagdo.pruebakeppri.service;

import com.santiagoagdo.pruebakeppri.model.Error.ErrorResponse;
import org.springframework.stereotype.Service;

@Service
public class ErrorService {

    public ErrorResponse createErrorResponse(String code, String message, Exception ex) {
        return new ErrorResponse(code, message + ": " + ex.getMessage());
    }

    public ErrorResponse createErrorResponse(String code, String message, String details) {
        return new ErrorResponse(code, message + ": " + details);
    }

    public ErrorResponse createErrorResponse(String code, String message) {
        return new ErrorResponse(code, message);
    }
}