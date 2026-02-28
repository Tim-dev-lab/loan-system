package com.radix.loan_system.exception;

import java.time.LocalDateTime;

public record ApiError(
        String message,
        LocalDateTime timestamp
) {}