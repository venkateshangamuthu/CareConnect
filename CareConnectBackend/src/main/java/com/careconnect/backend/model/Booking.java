package com.careconnect.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "orphanage_id", nullable = false)
    private Orphanage orphanage;

    private LocalDate eventDate;

    private String eventType; // Birthday, Anniversary, etc.

    @Column(columnDefinition = "TEXT")
    private String requirements;

    private BigDecimal advanceAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING,
        CONFIRMED,
        COMPLETED,
        CANCELLED
    }

    public Booking() {}

    public Booking(Long id, User user, Orphanage orphanage, LocalDate eventDate, String eventType, String requirements, BigDecimal advanceAmount, Status status) {
        this.id = id;
        this.user = user;
        this.orphanage = orphanage;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.requirements = requirements;
        this.advanceAmount = advanceAmount;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Orphanage getOrphanage() { return orphanage; }
    public void setOrphanage(Orphanage orphanage) { this.orphanage = orphanage; }

    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }

    public BigDecimal getAdvanceAmount() { return advanceAmount; }
    public void setAdvanceAmount(BigDecimal advanceAmount) { this.advanceAmount = advanceAmount; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
