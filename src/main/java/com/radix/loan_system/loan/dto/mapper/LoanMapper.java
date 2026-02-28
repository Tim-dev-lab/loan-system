package com.radix.loan_system.loan.dto.mapper;

import com.radix.loan_system.loan.Loan;
import com.radix.loan_system.loan.dto.LoanResponseDto;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public LoanResponseDto toResponseDto(Loan loan) {
        LoanResponseDto dto = new LoanResponseDto();
        dto.setLoanId(loan.getLoanId());
        dto.setLoanAmount(loan.getLoanAmount());
        dto.setRemainingBalance(loan.getRemainingBalance());
        dto.setTerm(loan.getTerm());
        dto.setStatus(loan.getStatus().name());
        dto.setCreatedAt(loan.getCreatedAt());
        return dto;
    }
}