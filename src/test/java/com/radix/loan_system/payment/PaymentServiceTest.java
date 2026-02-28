package com.radix.loan_system.payment;

import com.radix.loan_system.exception.BusinessException;
import com.radix.loan_system.loan.Loan;
import com.radix.loan_system.loan.LoanService;
import com.radix.loan_system.loan.LoanStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private LoanService loanService;

    @Test
    void shouldReduceBalanceAfterPayment() {

        Loan loan = loanService.createLoan(1000.0, 12);

        paymentService.processPayment(loan.getLoanId(), 500.0);

        Loan updated = loanService.getLoan(loan.getLoanId());

        assertEquals(500.0, updated.getRemainingBalance());
    }

    @Test
    void shouldThrowErrorOnOverpayment() {

        Loan loan = loanService.createLoan(1000.0, 12);

        assertThrows(
                BusinessException.class,
                () -> paymentService.processPayment(loan.getLoanId(), 1500.0)
        );
    }

    @Test
    void shouldSettleLoanWhenFullyPaid() {

        Loan loan = loanService.createLoan(1000.0, 12);

        paymentService.processPayment(loan.getLoanId(), 1000.0);

        Loan updated = loanService.getLoan(loan.getLoanId());

        assertEquals(LoanStatus.SETTLED, updated.getStatus());
    }
}