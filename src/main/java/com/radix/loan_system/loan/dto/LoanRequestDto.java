package com.radix.loan_system.loan.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Getter
@Setter
public class LoanRequestDto {

    @NotNull(message = "Loan amount is required")
    @Positive(message = "Loan amount must be greater than zero")
    private Double loanAmount;

    @NotNull(message = "Loan term is required")
    @Positive(message = "Loan term must be greater than zero")
    private Integer term;
}