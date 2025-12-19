package com.tour.traveltours.dto;

public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Long tourCount;
    
    public CategoryDTO() {
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public Long getTourCount() {
        return tourCount;
    }
    
    public void setTourCount(Long tourCount) {
        this.tourCount = tourCount;
    }
}
