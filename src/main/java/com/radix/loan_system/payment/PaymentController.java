package com.radix.loan_system.payment;

import com.radix.loan_system.payment.dto.PaymentRequestDto;
import com.radix.loan_system.payment.dto.PaymentResponseDto;
import com.radix.loan_system.payment.mapper.PaymentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> makePayment(
            @Valid @RequestBody PaymentRequestDto request) {

        Payment payment = paymentService.processPayment(
                request.getLoanId(),
                request.getPaymentAmount()
        );

        return ResponseEntity.ok(paymentMapper.toResponseDto(payment));
    }
}