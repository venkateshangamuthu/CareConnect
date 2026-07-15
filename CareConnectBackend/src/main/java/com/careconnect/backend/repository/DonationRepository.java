package com.careconnect.backend.repository;

import com.careconnect.backend.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByUserId(Long userId);
    List<Donation> findByOrphanageId(Long orphanageId);

    @Query("SELECT COALESCE(SUM(d.amount), 0) FROM Donation d WHERE d.orphanage.id = :orphanageId AND d.status = 'SUCCESS'")
    BigDecimal getTotalDonationsByOrphanageId(@Param("orphanageId") Long orphanageId);
}
