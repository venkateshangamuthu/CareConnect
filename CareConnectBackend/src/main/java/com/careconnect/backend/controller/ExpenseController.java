package com.careconnect.backend.controller;

import com.careconnect.backend.dto.ExpenseDto;
import com.careconnect.backend.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseDto> addExpense(@RequestBody ExpenseDto expenseDto) {
        return ResponseEntity.ok(expenseService.addExpense(expenseDto));
    }

    @GetMapping("/orphanage/{orphanageId}")
    public ResponseEntity<List<ExpenseDto>> getExpensesByOrphanage(@PathVariable Long orphanageId) {
        return ResponseEntity.ok(expenseService.getExpensesByOrphanage(orphanageId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
