package com.tour.traveltours.config;

import com.tour.traveltours.entity.*;
import com.tour.traveltours.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private TourPackageRepository tourPackageRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        initializeRoles();
        initializeAdminUser();
        initializeCategories();
        initializeTourPackages();
    }
    
    private void initializeRoles() {
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setName(Role.RoleName.ROLE_ADMIN);
            roleRepository.save(adminRole);
            
            Role userRole = new Role();
            userRole.setName(Role.RoleName.ROLE_USER);
            roleRepository.save(userRole);
        }
    }
    
    private void initializeAdminUser() {
        if (!userRepository.existsByEmail("admin@tours.com")) {
            User admin = new User();
            admin.setEmail("admin@tours.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setPhone("1234567890");
            admin.setActive(true);
            
            Set<Role> roles = new HashSet<>();
            Role adminRole = roleRepository.findByName(Role.RoleName.ROLE_ADMIN)
                    .orElseThrow();
            roles.add(adminRole);
            admin.setRoles(roles);
            
            userRepository.save(admin);
        }
    }
    
    private void initializeCategories() {
        if (categoryRepository.count() == 0) {
            Category beach = new Category();
            beach.setName("Beach Holidays");
            beach.setDescription("Relaxing beach destinations");
            beach.setImageUrl("https://images.unsplash.com/photo-1507525428034-b723cf961d3e");
            categoryRepository.save(beach);
            
            Category adventure = new Category();
            adventure.setName("Adventure Tours");
            adventure.setDescription("Thrilling adventure experiences");
            adventure.setImageUrl("https://images.unsplash.com/photo-1464822759844-d150ad6d0e0b");
            categoryRepository.save(adventure);
            
            Category cultural = new Category();
            cultural.setName("Cultural Tours");
            cultural.setDescription("Explore rich cultural heritage");
            cultural.setImageUrl("https://images.unsplash.com/photo-1515542622106-78bda8ba0e5b");
            categoryRepository.save(cultural);
            
            Category mountain = new Category();
            mountain.setName("Mountain Tours");
            mountain.setDescription("Mountain trekking and hiking");
            mountain.setImageUrl("https://images.unsplash.com/photo-1464822759844-d150ad6d0e0b");
            categoryRepository.save(mountain);
        }
    }
    
    private void initializeTourPackages() {
        if (tourPackageRepository.count() == 0) {
            Category beach = categoryRepository.findByName("Beach Holidays").orElse(null);
            Category adventure = categoryRepository.findByName("Adventure Tours").orElse(null);
            Category cultural = categoryRepository.findByName("Cultural Tours").orElse(null);
            
            if (beach != null) {
                TourPackage package1 = new TourPackage();
                package1.setTitle("Paradise Beach Resort");
                package1.setDestination("Maldives");
                package1.setDescription("Experience luxury in the beautiful Maldives");
                package1.setItinerary("Day 1: Arrival, Day 2-4: Beach activities, Day 5: Departure");
                package1.setDuration(5);
                package1.setPrice(new BigDecimal("1500.00"));
                package1.setDiscountPrice(new BigDecimal("1200.00"));
                package1.setMainImageUrl("https://images.unsplash.com/photo-1507525428034-b723cf961d3e");
                package1.setCategory(beach);
                package1.setActive(true);
                package1.setAvailableSeats(20);
                package1.setStartDate(LocalDate.now().plusDays(30));
                package1.setEndDate(LocalDate.now().plusDays(35));
                package1.setTourType("PREMIUM");
                tourPackageRepository.save(package1);
            }
            
            if (adventure != null) {
                TourPackage package2 = new TourPackage();
                package2.setTitle("Mountain Adventure Trek");
                package2.setDestination("Nepal");
                package2.setDescription("Trek through the Himalayas");
                package2.setItinerary("Day 1: Arrival, Day 2-7: Trekking, Day 8: Departure");
                package2.setDuration(8);
                package2.setPrice(new BigDecimal("2000.00"));
                package2.setCategory(adventure);
                package2.setActive(true);
                package2.setAvailableSeats(15);
                package2.setStartDate(LocalDate.now().plusDays(45));
                package2.setEndDate(LocalDate.now().plusDays(53));
                package2.setTourType("GROUP");
                tourPackageRepository.save(package2);
            }
            
            if (cultural != null) {
                TourPackage package3 = new TourPackage();
                package3.setTitle("Historical City Tour");
                package3.setDestination("Rome, Italy");
                package3.setDescription("Explore ancient Roman history");
                package3.setItinerary("Day 1: Arrival, Day 2-4: City tours, Day 5: Departure");
                package3.setDuration(5);
                package3.setPrice(new BigDecimal("1800.00"));
                package3.setDiscountPrice(new BigDecimal("1500.00"));
                package3.setCategory(cultural);
                package3.setActive(true);
                package3.setAvailableSeats(25);
                package3.setStartDate(LocalDate.now().plusDays(60));
                package3.setEndDate(LocalDate.now().plusDays(65));
                package3.setTourType("PRIVATE");
                tourPackageRepository.save(package3);
            }
        }
    }
}

