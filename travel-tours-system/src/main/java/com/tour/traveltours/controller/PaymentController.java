package com.tour.traveltours.controller;

import com.tour.traveltours.dto.PaymentDTO;
import com.tour.traveltours.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/payments")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("/create/{bookingId}")
    public ResponseEntity<PaymentDTO> createPayment(@PathVariable Long bookingId) {
        return ResponseEntity.ok(paymentService.createPayment(bookingId));
    }
    
    @PostMapping("/process/{paymentId}")
    public ResponseEntity<PaymentDTO> processPayment(
            @PathVariable String paymentId,
            @RequestParam String paymentMethod) {
        return ResponseEntity.ok(paymentService.processPayment(paymentId, paymentMethod));
    }
    
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<PaymentDTO> getPaymentByBookingId(@PathVariable Long bookingId) {
        return ResponseEntity.ok(paymentService.getPaymentByBookingId(bookingId));
    }
    
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentByPaymentId(@PathVariable String paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentByPaymentId(paymentId));
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
}

