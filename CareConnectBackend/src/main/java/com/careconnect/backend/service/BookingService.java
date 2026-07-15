package com.careconnect.backend.service;

import com.careconnect.backend.dto.BookingDto;
import com.careconnect.backend.model.Booking;
import com.careconnect.backend.model.Orphanage;
import com.careconnect.backend.model.User;
import com.careconnect.backend.repository.BookingRepository;
import com.careconnect.backend.repository.OrphanageRepository;
import com.careconnect.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final OrphanageRepository orphanageRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, OrphanageRepository orphanageRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.orphanageRepository = orphanageRepository;
    }

    public BookingDto createBooking(BookingDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Orphanage orphanage = orphanageRepository.findById(dto.getOrphanageId()).orElseThrow(() -> new RuntimeException("Orphanage not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setOrphanage(orphanage);
        booking.setEventDate(dto.getEventDate());
        booking.setEventType(dto.getEventType());
        booking.setRequirements(dto.getRequirements());
        booking.setAdvanceAmount(dto.getAdvanceAmount());
        booking.setStatus(Booking.Status.PENDING);

        return mapToDto(bookingRepository.save(booking));
    }

    public List<BookingDto> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<BookingDto> getBookingsByOrphanage(Long orphanageId) {
        return bookingRepository.findByOrphanageId(orphanageId).stream().map(this::mapToDto).collect(Collectors.toList());
    }
    
    public void updateBookingStatus(Long bookingId, Booking.Status status) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(status);
        bookingRepository.save(booking);
    }
    
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private BookingDto mapToDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setUserId(booking.getUser().getId());
        dto.setUserName(booking.getUser().getName());
        dto.setOrphanageId(booking.getOrphanage().getId());
        dto.setOrphanageName(booking.getOrphanage().getName());
        dto.setEventDate(booking.getEventDate());
        dto.setEventType(booking.getEventType());
        dto.setRequirements(booking.getRequirements());
        dto.setAdvanceAmount(booking.getAdvanceAmount());
        dto.setStatus(booking.getStatus().name());
        return dto;
    }
}
