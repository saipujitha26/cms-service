package com.cms.service.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing details of an error response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private String message;      // Error message
    private String error;        // Type of error
    private LocalDateTime timeStamp;  // Timestamp of the error
}
