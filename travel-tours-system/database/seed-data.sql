-- Seed Data for Travel Tours System
-- Run this after schema.sql

USE travel_tours_db;

-- Insert Sample Tour Packages
INSERT INTO tour_packages (title, destination, description, itinerary, duration, price, discount_price, main_image_url, category_id, active, available_seats, start_date, end_date, tour_type) VALUES
('Paradise Beach Resort', 'Maldives', 'Experience luxury in the beautiful Maldives', 'Day 1: Arrival, Day 2-4: Beach activities, Day 5: Departure', 5, 1500.00, 1200.00, 'https://images.unsplash.com/photo-1507525428034-b723cf961d3e', 
 (SELECT id FROM categories WHERE name = 'Beach Holidays' LIMIT 1), TRUE, 20, DATE_ADD(CURDATE(), INTERVAL 30 DAY), DATE_ADD(CURDATE(), INTERVAL 35 DAY), 'PREMIUM'),

('Mountain Adventure Trek', 'Nepal', 'Trek through the Himalayas', 'Day 1: Arrival, Day 2-7: Trekking, Day 8: Departure', 8, 2000.00, NULL, 'https://images.unsplash.com/photo-1464822759844-d150ad6d0e0b',
 (SELECT id FROM categories WHERE name = 'Adventure Tours' LIMIT 1), TRUE, 15, DATE_ADD(CURDATE(), INTERVAL 45 DAY), DATE_ADD(CURDATE(), INTERVAL 53 DAY), 'GROUP'),

('Historical City Tour', 'Rome, Italy', 'Explore ancient Roman history', 'Day 1: Arrival, Day 2-4: City tours, Day 5: Departure', 5, 1800.00, 1500.00, 'https://images.unsplash.com/photo-1515542622106-78bda8ba0e5b',
 (SELECT id FROM categories WHERE name = 'Cultural Tours' LIMIT 1), TRUE, 25, DATE_ADD(CURDATE(), INTERVAL 60 DAY), DATE_ADD(CURDATE(), INTERVAL 65 DAY), 'PRIVATE'),

('Tropical Island Escape', 'Bali, Indonesia', 'Relax in tropical paradise', 'Day 1: Arrival, Day 2-6: Island activities, Day 7: Departure', 7, 1400.00, 1100.00, 'https://images.unsplash.com/photo-1507525428034-b723cf961d3e',
 (SELECT id FROM categories WHERE name = 'Beach Holidays' LIMIT 1), TRUE, 18, DATE_ADD(CURDATE(), INTERVAL 40 DAY), DATE_ADD(CURDATE(), INTERVAL 47 DAY), 'GROUP'),

('Safari Adventure', 'Kenya', 'Wildlife safari experience', 'Day 1: Arrival, Day 2-5: Safari tours, Day 6: Departure', 6, 2200.00, NULL, 'https://images.unsplash.com/photo-1464822759844-d150ad6d0e0b',
 (SELECT id FROM categories WHERE name = 'Adventure Tours' LIMIT 1), TRUE, 12, DATE_ADD(CURDATE(), INTERVAL 50 DAY), DATE_ADD(CURDATE(), INTERVAL 56 DAY), 'PREMIUM')
ON DUPLICATE KEY UPDATE title=title;

