package com.tour.traveltours.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BookingRequest {
    
    @NotNull(message = "Tour package ID is required")
    private Long tourPackageId;
    
    @NotNull(message = "Number of travelers is required")
    @Min(value = 1, message = "At least 1 traveler is required")
    private Integer numberOfTravelers;
    
    private String specialRequests;
    
    public BookingRequest() {
    }
    
    public Long getTourPackageId() {
        return tourPackageId;
    }
    
    public void setTourPackageId(Long tourPackageId) {
        this.tourPackageId = tourPackageId;
    }
    
    public Integer getNumberOfTravelers() {
        return numberOfTravelers;
    }
    
    public void setNumberOfTravelers(Integer numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }
    
    public String getSpecialRequests() {
        return specialRequests;
    }
    
    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }
}
