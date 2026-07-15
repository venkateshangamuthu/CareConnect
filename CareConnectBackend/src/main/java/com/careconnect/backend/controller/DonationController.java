package com.careconnect.backend.controller;

import com.careconnect.backend.dto.DonationDto;
import com.careconnect.backend.service.DonationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping
    @PreAuthorize("hasRole('DONOR')")
    public ResponseEntity<DonationDto> makeDonation(@RequestBody DonationDto dto) {
        return ResponseEntity.ok(donationService.createDonation(dto));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('DONOR', 'ADMIN')")
    public ResponseEntity<List<DonationDto>> getUserDonations(@PathVariable Long userId) {
        return ResponseEntity.ok(donationService.getDonationsByUser(userId));
    }

    @GetMapping("/orphanage/{orphanageId}")
    @PreAuthorize("hasAnyRole('ORPHANAGE', 'ADMIN')")
    public ResponseEntity<List<DonationDto>> getOrphanageDonations(@PathVariable Long orphanageId) {
        return ResponseEntity.ok(donationService.getDonationsByOrphanage(orphanageId));
    }
}
