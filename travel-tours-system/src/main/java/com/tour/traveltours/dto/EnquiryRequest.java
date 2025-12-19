package com.tour.traveltours.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EnquiryRequest {
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Phone is required")
    private String phone;
    
    private String message;
    
    private Long tourPackageId;
    
    public EnquiryRequest() {
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public Long getTourPackageId() {
        return tourPackageId;
    }
    
    public void setTourPackageId(Long tourPackageId) {
        this.tourPackageId = tourPackageId;
    }
}
