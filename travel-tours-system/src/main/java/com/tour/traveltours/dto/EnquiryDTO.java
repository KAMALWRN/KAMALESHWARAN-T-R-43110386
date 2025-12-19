package com.tour.traveltours.dto;

import java.time.LocalDateTime;

public class EnquiryDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String message;
    private Long tourPackageId;
    private String tourPackageTitle;
    private Boolean isRead;
    private LocalDateTime createdAt;
    
    public EnquiryDTO() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public String getTourPackageTitle() {
        return tourPackageTitle;
    }
    
    public void setTourPackageTitle(String tourPackageTitle) {
        this.tourPackageTitle = tourPackageTitle;
    }
    
    public Boolean getIsRead() {
        return isRead;
    }
    
    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
