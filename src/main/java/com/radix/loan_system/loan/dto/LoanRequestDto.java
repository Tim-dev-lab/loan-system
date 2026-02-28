package com.radix.loan_system.loan.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanRequestDto {

    private Double loanAmount;
    private Integer term;
}