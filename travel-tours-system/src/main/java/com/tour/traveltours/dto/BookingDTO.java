package com.tour.traveltours.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingDTO {
    private Long id;
    private String bookingNumber;
    private Long userId;
    private String userName;
    private Long tourPackageId;
    private String tourPackageTitle;
    private Integer numberOfTravelers;
    private BigDecimal totalAmount;
    private String status;
    private String specialRequests;
    private LocalDateTime bookingDate;
    
    public BookingDTO() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getBookingNumber() {
        return bookingNumber;
    }
    
    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
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
    
    public Integer getNumberOfTravelers() {
        return numberOfTravelers;
    }
    
    public void setNumberOfTravelers(Integer numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getSpecialRequests() {
        return specialRequests;
    }
    
    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }
    
    public LocalDateTime getBookingDate() {
        return bookingDate;
    }
    
    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
}
