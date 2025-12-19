package com.tour.traveltours.controller;

import com.tour.traveltours.dto.NewsletterRequest;
import com.tour.traveltours.entity.NewsletterSubscriber;
import com.tour.traveltours.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/newsletter")
public class NewsletterController {
    
    @Autowired
    private NewsletterService newsletterService;
    
    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@Valid @RequestBody NewsletterRequest request) {
        NewsletterSubscriber subscriber = newsletterService.subscribe(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Successfully subscribed to newsletter!");
        response.put("email", subscriber.getEmail());
        return ResponseEntity.ok(response);
    }
}

