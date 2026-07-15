package com.careconnect.backend.service;

import com.careconnect.backend.dto.FinancialStatsDto;
import com.careconnect.backend.repository.DonationRepository;
import com.careconnect.backend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FinancialService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public FinancialStatsDto getFinancialStats(Long orphanageId) {
        BigDecimal income = donationRepository.getTotalDonationsByOrphanageId(orphanageId);
        if (income == null) income = BigDecimal.ZERO;

        BigDecimal expenses = expenseRepository.getTotalExpensesByOrphanageId(orphanageId);
        if (expenses == null) expenses = BigDecimal.ZERO;

        BigDecimal balance = income.subtract(expenses);

        return new FinancialStatsDto(income, expenses, balance);
    }
}
