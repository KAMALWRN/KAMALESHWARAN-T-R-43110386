package com.tour.traveltours.service;

import com.tour.traveltours.dto.BookingDTO;
import com.tour.traveltours.dto.BookingRequest;
import com.tour.traveltours.entity.Booking;
import com.tour.traveltours.entity.Payment;
import com.tour.traveltours.entity.TourPackage;
import com.tour.traveltours.entity.User;
import com.tour.traveltours.repository.BookingRepository;
import com.tour.traveltours.repository.PaymentRepository;
import com.tour.traveltours.repository.TourPackageRepository;
import com.tour.traveltours.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private TourPackageRepository tourPackageRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Transactional
    public BookingDTO createBooking(BookingRequest request) {
        String email = getCurrentUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        TourPackage tourPackage = tourPackageRepository.findById(request.getTourPackageId())
                .orElseThrow(() -> new RuntimeException("Tour package not found"));
        
        if (tourPackage.getAvailableSeats() < request.getNumberOfTravelers()) {
            throw new RuntimeException("Not enough available seats");
        }
        
        BigDecimal pricePerPerson = tourPackage.getDiscountPrice() != null && 
                tourPackage.getDiscountPrice().compareTo(BigDecimal.ZERO) > 0
                ? tourPackage.getDiscountPrice() : tourPackage.getPrice();
        
        BigDecimal totalAmount = pricePerPerson.multiply(new BigDecimal(request.getNumberOfTravelers()));
        
        Booking booking = new Booking();
        booking.setBookingNumber(generateBookingNumber());
        booking.setUser(user);
        booking.setTourPackage(tourPackage);
        booking.setNumberOfTravelers(request.getNumberOfTravelers());
        booking.setTotalAmount(totalAmount);
        booking.setStatus(Booking.BookingStatus.PENDING);
        booking.setSpecialRequests(request.getSpecialRequests());
        
        // Don't reduce seats until payment is confirmed
        // tourPackage.setAvailableSeats(tourPackage.getAvailableSeats() - request.getNumberOfTravelers());
        // tourPackageRepository.save(tourPackage);
        
        Booking saved = bookingRepository.save(booking);
        
        // Create pending payment
        Payment payment = new Payment();
        payment.setPaymentId("PAY" + UUID.randomUUID().toString().substring(0, 12).toUpperCase());
        payment.setBooking(saved);
        payment.setAmount(totalAmount);
        payment.setStatus(Payment.PaymentStatus.PENDING);
        payment.setPaymentMethod("CARD");
        paymentRepository.save(payment);
        
        return convertToDTO(saved);
    }
    
    public List<BookingDTO> getUserBookings() {
        String email = getCurrentUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return bookingRepository.findByUserId(user.getId()).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public BookingDTO updateBookingStatus(Long id, Booking.BookingStatus status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(status);
        Booking saved = bookingRepository.save(booking);
        return convertToDTO(saved);
    }
    
    private String generateBookingNumber() {
        return "GT" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    private String getCurrentUserEmail() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }
    
    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setBookingNumber(booking.getBookingNumber());
        dto.setUserId(booking.getUser().getId());
        dto.setUserName(booking.getUser().getFirstName() + " " + booking.getUser().getLastName());
        dto.setTourPackageId(booking.getTourPackage().getId());
        dto.setTourPackageTitle(booking.getTourPackage().getTitle());
        dto.setNumberOfTravelers(booking.getNumberOfTravelers());
        dto.setTotalAmount(booking.getTotalAmount());
        dto.setStatus(booking.getStatus().name());
        dto.setSpecialRequests(booking.getSpecialRequests());
        dto.setBookingDate(booking.getBookingDate());
        return dto;
    }
}

