package com.radix.loan_system.payment.mapper;


import com.radix.loan_system.payment.Payment;
import com.radix.loan_system.payment.dto.PaymentResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponseDto toResponseDto(Payment payment) {
        PaymentResponseDto dto = new PaymentResponseDto();
        dto.setPaymentId(payment.getPaymentId());
        dto.setLoanId(payment.getLoanId());
        dto.setPaymentAmount(payment.getPaymentAmount());
        dto.setCreatedAt(payment.getCreatedAt());
        return dto;
    }
}