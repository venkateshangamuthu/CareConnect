package com.careconnect.backend.controller;

import com.careconnect.backend.dto.BookingDto;
import com.careconnect.backend.model.Booking;
import com.careconnect.backend.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @PreAuthorize("hasRole('DONOR')")
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto dto) {
        return ResponseEntity.ok(bookingService.createBooking(dto));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('DONOR', 'ADMIN')")
    public ResponseEntity<List<BookingDto>> getUserBookings(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUser(userId));
    }

    @GetMapping("/orphanage/{orphanageId}")
    @PreAuthorize("hasAnyRole('ORPHANAGE', 'ADMIN')")
    public ResponseEntity<List<BookingDto>> getOrphanageBookings(@PathVariable Long orphanageId) {
        return ResponseEntity.ok(bookingService.getBookingsByOrphanage(orphanageId));
    }
    
    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ORPHANAGE', 'ADMIN')")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam Booking.Status status) {
        bookingService.updateBookingStatus(id, status);
        return ResponseEntity.ok().build();
    }
}
