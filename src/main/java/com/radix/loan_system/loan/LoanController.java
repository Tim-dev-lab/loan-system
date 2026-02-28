package com.radix.loan_system.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan request) {
        Loan loan = loanService.createLoan(
                request.getLoanAmount(),
                request.getTerm()
        );
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoan(@PathVariable String loanId) {
        return ResponseEntity.ok(loanService.getLoan(loanId));
    }
}