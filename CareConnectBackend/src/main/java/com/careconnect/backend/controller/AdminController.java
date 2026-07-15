package com.careconnect.backend.controller;

import com.careconnect.backend.dto.BookingDto;
import com.careconnect.backend.dto.DonationDto;
import com.careconnect.backend.dto.OrphanageDto;
import com.careconnect.backend.service.BookingService;
import com.careconnect.backend.service.DonationService;
import com.careconnect.backend.service.OrphanageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final OrphanageService orphanageService;
    private final DonationService donationService;
    private final BookingService bookingService;

    public AdminController(OrphanageService orphanageService, DonationService donationService, BookingService bookingService) {
        this.orphanageService = orphanageService;
        this.donationService = donationService;
        this.bookingService = bookingService;
    }

    @GetMapping("/orphanages/pending")
    public ResponseEntity<List<OrphanageDto>> getPendingOrphanages() {
        return ResponseEntity.ok(orphanageService.getPendingOrphanages());
    }

    @PutMapping("/orphanages/{id}/approve")
    public ResponseEntity<Void> approveOrphanage(@PathVariable Long id) {
        orphanageService.approveOrphanage(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/donations")
    public ResponseEntity<List<DonationDto>> getAllDonations() {
        return ResponseEntity.ok(donationService.getAllDonations());
    }
    
    @GetMapping("/bookings")
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}
