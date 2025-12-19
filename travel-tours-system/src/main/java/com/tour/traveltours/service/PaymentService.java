package com.tour.traveltours.service;

import com.tour.traveltours.dto.PaymentDTO;
import com.tour.traveltours.entity.Booking;
import com.tour.traveltours.entity.Payment;
import com.tour.traveltours.entity.TourPackage;
import com.tour.traveltours.repository.BookingRepository;
import com.tour.traveltours.repository.PaymentRepository;
import com.tour.traveltours.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private TourPackageRepository tourPackageRepository;
    
    @Transactional
    public PaymentDTO createPayment(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        // Check if payment already exists
        Payment existingPayment = paymentRepository.findByBookingId(bookingId).orElse(null);
        if (existingPayment != null) {
            return convertToDTO(existingPayment);
        }
        
        Payment payment = new Payment();
        payment.setPaymentId(generatePaymentId());
        payment.setBooking(booking);
        payment.setAmount(booking.getTotalAmount());
        payment.setStatus(Payment.PaymentStatus.PENDING);
        payment.setPaymentMethod("CARD"); // Default for fake payment
        
        Payment saved = paymentRepository.save(payment);
        return convertToDTO(saved);
    }
    
    @Transactional
    public PaymentDTO processPayment(String paymentId, String paymentMethod) {
        Payment payment = paymentRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        
        if (payment.getStatus() != Payment.PaymentStatus.PENDING) {
            throw new RuntimeException("Payment already processed");
        }
        
        // Simulate fake payment processing - always succeeds
        payment.setStatus(Payment.PaymentStatus.COMPLETED);
        payment.setPaymentMethod(paymentMethod);
        payment.setTransactionId("TXN" + UUID.randomUUID().toString().substring(0, 12).toUpperCase());
        
        // Update booking status to CONFIRMED
        Booking booking = payment.getBooking();
        booking.setStatus(Booking.BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
        
        // Reduce available seats when payment is confirmed
        TourPackage tourPackage = booking.getTourPackage();
        tourPackage.setAvailableSeats(tourPackage.getAvailableSeats() - booking.getNumberOfTravelers());
        tourPackageRepository.save(tourPackage);
        
        Payment saved = paymentRepository.save(payment);
        return convertToDTO(saved);
    }
    
    public PaymentDTO getPaymentByBookingId(Long bookingId) {
        Payment payment = paymentRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return convertToDTO(payment);
    }
    
    public PaymentDTO getPaymentByPaymentId(String paymentId) {
        Payment payment = paymentRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return convertToDTO(payment);
    }
    
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAllByOrderByPaymentDateDesc().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    private String generatePaymentId() {
        return "PAY" + UUID.randomUUID().toString().substring(0, 12).toUpperCase();
    }
    
    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setPaymentId(payment.getPaymentId());
        dto.setBookingId(payment.getBooking().getId());
        dto.setBookingNumber(payment.getBooking().getBookingNumber());
        dto.setUserName(payment.getBooking().getUser().getFirstName() + " " + 
                        payment.getBooking().getUser().getLastName());
        dto.setTourPackageTitle(payment.getBooking().getTourPackage().getTitle());
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus().name());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setTransactionId(payment.getTransactionId());
        dto.setPaymentDate(payment.getPaymentDate());
        return dto;
    }
}

