package com.tour.traveltours.service;

import com.tour.traveltours.dto.TourPackageDTO;
import com.tour.traveltours.entity.Category;
import com.tour.traveltours.entity.TourPackage;
import com.tour.traveltours.repository.CategoryRepository;
import com.tour.traveltours.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourPackageService {
    
    @Autowired
    private TourPackageRepository tourPackageRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<TourPackageDTO> getAllActivePackages() {
        return tourPackageRepository.findByActiveTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<TourPackageDTO> getPackagesByCategory(Long categoryId) {
        return tourPackageRepository.findByActiveTrueAndCategoryId(categoryId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public TourPackageDTO getPackageById(Long id) {
        TourPackage tourPackage = tourPackageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour package not found"));
        return convertToDTO(tourPackage);
    }
    
    private TourPackageDTO convertToDTO(TourPackage tourPackage) {
        TourPackageDTO dto = new TourPackageDTO();
        dto.setId(tourPackage.getId());
        dto.setTitle(tourPackage.getTitle());
        dto.setDestination(tourPackage.getDestination());
        dto.setDescription(tourPackage.getDescription());
        dto.setItinerary(tourPackage.getItinerary());
        dto.setDuration(tourPackage.getDuration());
        dto.setPrice(tourPackage.getPrice());
        dto.setDiscountPrice(tourPackage.getDiscountPrice());
        dto.setMainImageUrl(tourPackage.getMainImageUrl());
        dto.setImageUrls(tourPackage.getImageUrls());
        dto.setCategoryId(tourPackage.getCategory().getId());
        dto.setCategoryName(tourPackage.getCategory().getName());
        dto.setActive(tourPackage.getActive());
        dto.setAvailableSeats(tourPackage.getAvailableSeats());
        dto.setStartDate(tourPackage.getStartDate());
        dto.setEndDate(tourPackage.getEndDate());
        dto.setTourType(tourPackage.getTourType());
        return dto;
    }
}

