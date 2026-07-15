package com.careconnect.backend.repository;

import com.careconnect.backend.model.Orphanage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrphanageRepository extends JpaRepository<Orphanage, Long> {
    List<Orphanage> findByLocationContainingIgnoreCaseOrNameContainingIgnoreCase(String location, String name);
    List<Orphanage> findByIsApprovedTrue();
    List<Orphanage> findByIsApprovedFalse();
    Optional<Orphanage> findByUserId(Long userId);
}
