package com.radix.loan_system.payment.dto;


import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Getter
@Setter
public class PaymentRequestDto {

    @NotBlank(message = "Loan ID is required")
    private String loanId;

    @NotNull(message = "Payment amount is required")
    @Positive(message = "Payment amount must be greater than zero")
    private Double paymentAmount;
}