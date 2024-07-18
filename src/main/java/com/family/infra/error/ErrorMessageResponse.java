package com.family.infra.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessageResponse {
    private String status;
    private LocalDateTime timestamp;
}