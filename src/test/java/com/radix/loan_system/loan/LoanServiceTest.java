package com.radix.loan_system.loan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LoanServiceTest {

    @Autowired
    private LoanService loanService;

    @Test
    void shouldCreateLoan() {
        Loan loan = loanService.createLoan(1000.0, 12);

        assertNotNull(loan.getLoanId());
        assertEquals(1000.0, loan.getRemainingBalance());
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
    }
}