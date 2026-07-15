package com.careconnect.backend.service;

import com.careconnect.backend.dto.OrphanageDto;
import com.careconnect.backend.model.Orphanage;
import com.careconnect.backend.model.User;
import com.careconnect.backend.repository.OrphanageRepository;
import com.careconnect.backend.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrphanageService {

    private final OrphanageRepository orphanageRepository;
    private final UserRepository userRepository;

    public OrphanageService(OrphanageRepository orphanageRepository, UserRepository userRepository) {
        this.orphanageRepository = orphanageRepository;
        this.userRepository = userRepository;
    }

    public OrphanageDto createOrphanageProfile(Long userId, OrphanageDto dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Orphanage orphanage = new Orphanage();
        BeanUtils.copyProperties(dto, orphanage);
        orphanage.setUser(user);
        orphanage.setApproved(false); // require admin approval
        Orphanage saved = orphanageRepository.save(orphanage);
        return mapToDto(saved);
    }
    
    public OrphanageDto updateOrphanageProfile(Long orphanageId, OrphanageDto dto) {
        Orphanage orphanage = orphanageRepository.findById(orphanageId).orElseThrow(() -> new RuntimeException("Orphanage not found"));
        orphanage.setName(dto.getName());
        orphanage.setLocation(dto.getLocation());
        orphanage.setDescription(dto.getDescription());
        orphanage.setContactInfo(dto.getContactInfo());
        orphanage.setChildCount(dto.getChildCount());
        orphanage.setNeeds(dto.getNeeds());
        if(dto.getImageUrl() != null) orphanage.setImageUrl(dto.getImageUrl());

        orphanage.setBankAccountName(dto.getBankAccountName());
        orphanage.setBankName(dto.getBankName());
        orphanage.setAccountNumber(dto.getAccountNumber());
        orphanage.setIfscCode(dto.getIfscCode());
        orphanage.setUpiId(dto.getUpiId());
        if(dto.getQrCodeUrl() != null) orphanage.setQrCodeUrl(dto.getQrCodeUrl());
        if(dto.getPowerBiUrl() != null) orphanage.setPowerBiUrl(dto.getPowerBiUrl());
        
        return mapToDto(orphanageRepository.save(orphanage));
    }

    public List<OrphanageDto> getAllApprovedOrphanages() {
        return orphanageRepository.findByIsApprovedTrue().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<OrphanageDto> searchOrphanages(String query) {
        return orphanageRepository.findByLocationContainingIgnoreCaseOrNameContainingIgnoreCase(query, query)
                .stream().filter(Orphanage::isApproved).map(this::mapToDto).collect(Collectors.toList());
    }

    public OrphanageDto getOrphanageById(Long id) {
        Orphanage orphanage = orphanageRepository.findById(id).orElseThrow(() -> new RuntimeException("Orphanage not found"));
        return mapToDto(orphanage);
    }
    
    public OrphanageDto getOrphanageByUserId(Long userId) {
        Orphanage orphanage = orphanageRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Orphanage profile not found"));
        return mapToDto(orphanage);
    }

    // Admin methods
    public List<OrphanageDto> getPendingOrphanages() {
        return orphanageRepository.findByIsApprovedFalse().stream().map(this::mapToDto).collect(Collectors.toList());
    }
    
    public void approveOrphanage(Long id) {
        Orphanage orphanage = orphanageRepository.findById(id).orElseThrow(() -> new RuntimeException("Orphanage not found"));
        orphanage.setApproved(true);
        orphanageRepository.save(orphanage);
    }

    private OrphanageDto mapToDto(Orphanage orphanage) {
        OrphanageDto dto = new OrphanageDto();
        BeanUtils.copyProperties(orphanage, dto);
        dto.setUserId(orphanage.getUser().getId());
        return dto;
    }
}
