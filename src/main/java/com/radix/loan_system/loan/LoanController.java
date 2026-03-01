package com.radix.loan_system.loan;

import com.radix.loan_system.loan.dto.LoanRequestDto;
import com.radix.loan_system.loan.dto.LoanResponseDto;
import com.radix.loan_system.loan.mapper.LoanMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final LoanMapper loanMapper;

    @PostMapping
    public ResponseEntity<LoanResponseDto> createLoan(
            @Valid @RequestBody LoanRequestDto request) {

        Loan loan = loanService.createLoan(
                request.getLoanAmount(),
                request.getTerm()
        );

        return ResponseEntity.ok(loanMapper.toResponseDto(loan));
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanResponseDto> getLoan(
            @PathVariable String loanId) {

        Loan loan = loanService.getLoan(loanId);
        return ResponseEntity.ok(loanMapper.toResponseDto(loan));
    }
}