package com.careconnect.backend.service;

import com.careconnect.backend.dto.DonationDto;
import com.careconnect.backend.model.Donation;
import com.careconnect.backend.model.Orphanage;
import com.careconnect.backend.model.User;
import com.careconnect.backend.repository.DonationRepository;
import com.careconnect.backend.repository.OrphanageRepository;
import com.careconnect.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final OrphanageRepository orphanageRepository;

    public DonationService(DonationRepository donationRepository, UserRepository userRepository, OrphanageRepository orphanageRepository) {
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
        this.orphanageRepository = orphanageRepository;
    }

    public DonationDto createDonation(DonationDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Orphanage orphanage = orphanageRepository.findById(dto.getOrphanageId()).orElseThrow(() -> new RuntimeException("Orphanage not found"));

        Donation donation = new Donation();
        donation.setUser(user);
        donation.setOrphanage(orphanage);
        donation.setAmount(dto.getAmount());
        donation.setDate(LocalDateTime.now());
        donation.setStatus("SUCCESS"); // In real app, this comes from payment gateway callback
        donation.setTransactionId(UUID.randomUUID().toString());

        return mapToDto(donationRepository.save(donation));
    }

    public List<DonationDto> getDonationsByUser(Long userId) {
        return donationRepository.findByUserId(userId).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<DonationDto> getDonationsByOrphanage(Long orphanageId) {
        return donationRepository.findByOrphanageId(orphanageId).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<DonationDto> getAllDonations() {
        return donationRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private DonationDto mapToDto(Donation donation) {
        DonationDto dto = new DonationDto();
        dto.setId(donation.getId());
        dto.setUserId(donation.getUser().getId());
        dto.setUserName(donation.getUser().getName());
        dto.setOrphanageId(donation.getOrphanage().getId());
        dto.setOrphanageName(donation.getOrphanage().getName());
        dto.setAmount(donation.getAmount());
        dto.setTransactionId(donation.getTransactionId());
        dto.setDate(donation.getDate());
        dto.setStatus(donation.getStatus());
        return dto;
    }
}
