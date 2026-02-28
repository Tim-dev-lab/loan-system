package com.radix.loan_system.payment;

import com.radix.loan_system.exception.BusinessException;
import com.radix.loan_system.loan.Loan;
import com.radix.loan_system.loan.LoanService;
import com.radix.loan_system.loan.LoanStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final LoanService loanService;

    @Transactional
    public Payment processPayment(String loanId, Double amount) {

        Loan loan = fetchActiveLoan(loanId);

        validatePaymentAmount(loan, amount);

        updateLoanBalance(loan, amount);

        return recordPayment(loanId, amount);
    }

    private Loan fetchActiveLoan(String loanId) {
        Loan loan = loanService.getLoan(loanId);
        if (loan.getStatus() == LoanStatus.SETTLED) {
            throw new BusinessException("This loan is already fully repaid.");
        }
        return loan;
    }

    private void validatePaymentAmount(Loan loan, Double amount) {
        if (amount <= 0) {
            throw new BusinessException("Payment must be greater than zero.");
        }
        if (amount > loan.getRemainingBalance()) {
            throw new BusinessException("Payment exceeds remaining loan balance.");
        }
    }

    private void updateLoanBalance(Loan loan, Double amount) {
        loan.setRemainingBalance(loan.getRemainingBalance() - amount);
        if (loan.getRemainingBalance() == 0) {
            loan.setStatus(LoanStatus.SETTLED);
        }
        loanService.save(loan);
    }

    private Payment recordPayment(String loanId, Double amount) {
        Payment payment = new Payment();
        payment.setLoanId(loanId);
        payment.setPaymentAmount(amount);
        return paymentRepository.save(payment);
    }
}
