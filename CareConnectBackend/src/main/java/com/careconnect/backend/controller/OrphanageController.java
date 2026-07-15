package com.careconnect.backend.controller;

import com.careconnect.backend.dto.OrphanageDto;
import com.careconnect.backend.service.OrphanageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.careconnect.backend.dto.FinancialStatsDto;
import com.careconnect.backend.service.FinancialService;

@RestController
@RequestMapping("/api/orphanages")
public class OrphanageController {

    private final OrphanageService orphanageService;
    private final FinancialService financialService;

    public OrphanageController(OrphanageService orphanageService, FinancialService financialService) {
        this.orphanageService = orphanageService;
        this.financialService = financialService;
    }

    // Public endpoints
    @GetMapping("/public/list")
    public ResponseEntity<List<OrphanageDto>> getAllApprovedOrphanages() {
        return ResponseEntity.ok(orphanageService.getAllApprovedOrphanages());
    }

    @GetMapping("/public/search")
    public ResponseEntity<List<OrphanageDto>> searchOrphanages(@RequestParam String query) {
        return ResponseEntity.ok(orphanageService.searchOrphanages(query));
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<OrphanageDto> getOrphanageById(@PathVariable Long id) {
        return ResponseEntity.ok(orphanageService.getOrphanageById(id));
    }

    @GetMapping("/public/{id}/financial-stats")
    public ResponseEntity<FinancialStatsDto> getFinancialStats(@PathVariable Long id) {
        return ResponseEntity.ok(financialService.getFinancialStats(id));
    }

    // Orphanage role endpoints
    @PostMapping
    @PreAuthorize("hasRole('ORPHANAGE')")
    public ResponseEntity<OrphanageDto> createProfile(@RequestParam Long userId, @RequestBody OrphanageDto dto) {
        return ResponseEntity.ok(orphanageService.createOrphanageProfile(userId, dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ORPHANAGE') or hasRole('ADMIN')")
    public ResponseEntity<OrphanageDto> updateProfile(@PathVariable Long id, @RequestBody OrphanageDto dto) {
        return ResponseEntity.ok(orphanageService.updateOrphanageProfile(id, dto));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ORPHANAGE') or hasRole('ADMIN')")
    public ResponseEntity<OrphanageDto> getOrphanageByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(orphanageService.getOrphanageByUserId(userId));
    }
}
