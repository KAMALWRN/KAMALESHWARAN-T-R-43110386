package com.tour.traveltours.controller;

import com.tour.traveltours.dto.EnquiryDTO;
import com.tour.traveltours.dto.EnquiryRequest;
import com.tour.traveltours.service.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/enquiries")
public class EnquiryController {
    
    @Autowired
    private EnquiryService enquiryService;
    
    @PostMapping("/public/create")
    public ResponseEntity<EnquiryDTO> createEnquiry(@Valid @RequestBody EnquiryRequest request) {
        return ResponseEntity.ok(enquiryService.createEnquiry(request));
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EnquiryDTO>> getAllEnquiries() {
        return ResponseEntity.ok(enquiryService.getAllEnquiries());
    }
    
    @GetMapping("/unread")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EnquiryDTO>> getUnreadEnquiries() {
        return ResponseEntity.ok(enquiryService.getUnreadEnquiries());
    }
    
    @PutMapping("/{id}/read")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EnquiryDTO> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(enquiryService.markAsRead(id));
    }
}

