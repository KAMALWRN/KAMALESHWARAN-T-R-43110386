package com.tour.traveltours.controller;

import com.tour.traveltours.dto.TourPackageDTO;
import com.tour.traveltours.service.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/packages")
public class TourPackageController {
    
    @Autowired
    private TourPackageService tourPackageService;
    
    @GetMapping("/public/all")
    public ResponseEntity<List<TourPackageDTO>> getAllPackages() {
        return ResponseEntity.ok(tourPackageService.getAllActivePackages());
    }
    
    @GetMapping("/public/{id}")
    public ResponseEntity<TourPackageDTO> getPackageById(@PathVariable Long id) {
        return ResponseEntity.ok(tourPackageService.getPackageById(id));
    }
    
    @GetMapping("/public/category/{categoryId}")
    public ResponseEntity<List<TourPackageDTO>> getPackagesByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(tourPackageService.getPackagesByCategory(categoryId));
    }
}

