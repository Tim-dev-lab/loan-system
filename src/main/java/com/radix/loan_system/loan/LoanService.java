package com.radix.loan_system.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    public Loan createLoan(Double amount, Integer term) {
        Loan loan = new Loan();
        loan.setLoanAmount(amount);
        loan.setRemainingBalance(amount);
        loan.setTerm(term);
        loan.setStatus(LoanStatus.ACTIVE);

        return loanRepository.save(loan);
    }

    public Loan getLoan(String loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }
}