package com.radix.loan_system.loan.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoanResponseDto {

    private String loanId;
    private Double loanAmount;
    private Double remainingBalance;
    private Integer term;
    private String status;
    private LocalDateTime createdAt;
}