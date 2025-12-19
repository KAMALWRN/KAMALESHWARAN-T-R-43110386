package com.tour.traveltours.repository;

import com.tour.traveltours.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
    List<Enquiry> findByIsReadFalse();
    List<Enquiry> findAllByOrderByCreatedAtDesc();
}

