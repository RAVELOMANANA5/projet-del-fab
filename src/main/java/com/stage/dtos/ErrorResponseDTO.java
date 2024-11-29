package com.stage.dtos;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String message, Throwable cause, HttpStatus status, LocalDateTime timestamp) {
    public ErrorResponseDTO {
    }
}
