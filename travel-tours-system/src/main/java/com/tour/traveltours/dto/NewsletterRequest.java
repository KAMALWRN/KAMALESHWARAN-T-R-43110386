package com.tour.traveltours.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NewsletterRequest {
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    public NewsletterRequest() {
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
