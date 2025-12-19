package com.tour.traveltours.service;

import com.tour.traveltours.dto.EnquiryDTO;
import com.tour.traveltours.dto.EnquiryRequest;
import com.tour.traveltours.entity.Enquiry;
import com.tour.traveltours.entity.TourPackage;
import com.tour.traveltours.repository.EnquiryRepository;
import com.tour.traveltours.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnquiryService {
    
    @Autowired
    private EnquiryRepository enquiryRepository;
    
    @Autowired
    private TourPackageRepository tourPackageRepository;
    
    public EnquiryDTO createEnquiry(EnquiryRequest request) {
        Enquiry enquiry = new Enquiry();
        enquiry.setName(request.getName());
        enquiry.setEmail(request.getEmail());
        enquiry.setPhone(request.getPhone());
        enquiry.setMessage(request.getMessage());
        
        if (request.getTourPackageId() != null) {
            TourPackage tourPackage = tourPackageRepository.findById(request.getTourPackageId())
                    .orElse(null);
            enquiry.setTourPackage(tourPackage);
        }
        
        Enquiry saved = enquiryRepository.save(enquiry);
        return convertToDTO(saved);
    }
    
    public List<EnquiryDTO> getAllEnquiries() {
        return enquiryRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<EnquiryDTO> getUnreadEnquiries() {
        return enquiryRepository.findByIsReadFalse().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public EnquiryDTO markAsRead(Long id) {
        Enquiry enquiry = enquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquiry not found"));
        enquiry.setIsRead(true);
        Enquiry saved = enquiryRepository.save(enquiry);
        return convertToDTO(saved);
    }
    
    private EnquiryDTO convertToDTO(Enquiry enquiry) {
        EnquiryDTO dto = new EnquiryDTO();
        dto.setId(enquiry.getId());
        dto.setName(enquiry.getName());
        dto.setEmail(enquiry.getEmail());
        dto.setPhone(enquiry.getPhone());
        dto.setMessage(enquiry.getMessage());
        if (enquiry.getTourPackage() != null) {
            dto.setTourPackageId(enquiry.getTourPackage().getId());
            dto.setTourPackageTitle(enquiry.getTourPackage().getTitle());
        }
        dto.setIsRead(enquiry.getIsRead());
        dto.setCreatedAt(enquiry.getCreatedAt());
        return dto;
    }
}

