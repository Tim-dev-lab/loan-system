package com.radix.loan_system.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {

    private String loanId;
    private Double paymentAmount;
}