package com.careconnect.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingDto {
    private Long id;
    private Long userId;
    private String userName;
    private Long orphanageId;
    private String orphanageName;
    private LocalDate eventDate;
    private String eventType;
    private String requirements;
    private BigDecimal advanceAmount;
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
    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }
    public BigDecimal getAdvanceAmount() { return advanceAmount; }
    public void setAdvanceAmount(BigDecimal advanceAmount) { this.advanceAmount = advanceAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
