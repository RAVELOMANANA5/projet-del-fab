package com.stage.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        @JsonProperty("message") String message,
        @JsonProperty("cause") Throwable cause,
        @JsonProperty("status") HttpStatus status,
        @JsonProperty("timestamp") LocalDateTime timestamp)
{
}
