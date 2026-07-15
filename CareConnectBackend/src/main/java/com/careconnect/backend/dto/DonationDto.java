package com.careconnect.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DonationDto {
    private Long id;
    private Long userId;
    private String userName;
    private Long orphanageId;
    private String orphanageName;
    private BigDecimal amount;
    private String transactionId;
    private LocalDateTime date;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public Long getOrphanageId() { return orphanageId; }
    public void setOrphanageId(Long orphanageId) { this.orphanageId = orphanageId; }
    public String getOrphanageName() { return orphanageName; }
    public void setOrphanageName(String orphanageName) { this.orphanageName = orphanageName; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
