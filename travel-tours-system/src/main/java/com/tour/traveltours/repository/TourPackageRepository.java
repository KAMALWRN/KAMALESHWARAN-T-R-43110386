package com.tour.traveltours.repository;

import com.tour.traveltours.entity.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourPackageRepository extends JpaRepository<TourPackage, Long> {
    List<TourPackage> findByActiveTrue();
    List<TourPackage> findByCategoryId(Long categoryId);
    List<TourPackage> findByActiveTrueAndCategoryId(Long categoryId);
}

