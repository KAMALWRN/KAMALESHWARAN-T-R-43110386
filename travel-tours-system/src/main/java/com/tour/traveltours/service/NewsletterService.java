package com.tour.traveltours.service;

import com.tour.traveltours.dto.NewsletterRequest;
import com.tour.traveltours.entity.NewsletterSubscriber;
import com.tour.traveltours.repository.NewsletterSubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsletterService {
    
    @Autowired
    private NewsletterSubscriberRepository newsletterSubscriberRepository;
    
    public NewsletterSubscriber subscribe(NewsletterRequest request) {
        if (newsletterSubscriberRepository.existsByEmail(request.getEmail())) {
            NewsletterSubscriber existing = newsletterSubscriberRepository.findByEmail(request.getEmail())
                    .orElseThrow();
            if (!existing.getActive()) {
                existing.setActive(true);
                return newsletterSubscriberRepository.save(existing);
            }
            throw new RuntimeException("Email already subscribed");
        }
        
        NewsletterSubscriber subscriber = new NewsletterSubscriber();
        subscriber.setEmail(request.getEmail());
        subscriber.setActive(true);
        return newsletterSubscriberRepository.save(subscriber);
    }
}

