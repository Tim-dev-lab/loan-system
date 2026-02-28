package com.radix.loan_system.payment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentResponseDto {

    private String paymentId;
    private String loanId;
    private Double paymentAmount;
    private LocalDateTime createdAt;
}