package com.careconnect.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseDto {
    private Long id;
    private Long orphanageId;
    private String title;
    private String description;
    private BigDecimal amount;
    private LocalDate expenseDate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getOrphanageId() { return orphanageId; }
    public void setOrphanageId(Long orphanageId) { this.orphanageId = orphanageId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getExpenseDate() { return expenseDate; }
    public void setExpenseDate(LocalDate expenseDate) { this.expenseDate = expenseDate; }
}
