package com.tour.traveltours.repository;

import com.tour.traveltours.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByBookingId(Long bookingId);
    Optional<Payment> findByPaymentId(String paymentId);
    List<Payment> findAllByOrderByPaymentDateDesc();
}

