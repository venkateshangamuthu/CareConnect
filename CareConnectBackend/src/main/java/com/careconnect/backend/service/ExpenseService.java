package com.careconnect.backend.service;

import com.careconnect.backend.dto.ExpenseDto;
import com.careconnect.backend.model.Expense;
import com.careconnect.backend.model.Orphanage;
import com.careconnect.backend.repository.ExpenseRepository;
import com.careconnect.backend.repository.OrphanageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private OrphanageRepository orphanageRepository;

    public ExpenseDto addExpense(ExpenseDto expenseDto) {
        Orphanage orphanage = orphanageRepository.findById(expenseDto.getOrphanageId())
                .orElseThrow(() -> new RuntimeException("Orphanage not found"));

        Expense expense = new Expense();
        expense.setOrphanage(orphanage);
        expense.setTitle(expenseDto.getTitle());
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setExpenseDate(expenseDto.getExpenseDate());

        Expense savedExpense = expenseRepository.save(expense);
        return mapToDto(savedExpense);
    }

    public List<ExpenseDto> getExpensesByOrphanage(Long orphanageId) {
        List<Expense> expenses = expenseRepository.findByOrphanageId(orphanageId);
        return expenses.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    private ExpenseDto mapToDto(Expense expense) {
        ExpenseDto dto = new ExpenseDto();
        dto.setId(expense.getId());
        dto.setOrphanageId(expense.getOrphanage().getId());
        dto.setTitle(expense.getTitle());
        dto.setDescription(expense.getDescription());
        dto.setAmount(expense.getAmount());
        dto.setExpenseDate(expense.getExpenseDate());
        return dto;
    }
}
