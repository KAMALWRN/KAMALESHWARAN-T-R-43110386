# Database Setup Instructions

## Quick Setup

1. **Create Database:**
   ```sql
   CREATE DATABASE travel_tours_db;
   ```

2. **Run Schema:**
   - Open MySQL Command Line or MySQL Workbench
   - Run `schema.sql` file
   - This creates all tables and inserts default data

3. **Run Seed Data (Optional):**
   - Run `seed-data.sql` for sample tour packages
   - Or let the application create data automatically via DataInitializer

## Default Credentials

**Admin User:**
- Email: `admin@tours.com`
- Password: `admin123`

## Database Configuration

Update `src/main/resources/application.properties` if your MySQL settings are different:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/travel_tours_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
```

## Tables Created

1. `roles` - User roles (ADMIN, USER)
2. `users` - User accounts
3. `user_roles` - User-Role mapping
4. `categories` - Tour categories
5. `tour_packages` - Tour packages
6. `tour_package_images` - Package images
7. `bookings` - User bookings
8. `enquiries` - Customer enquiries
9. `newsletter_subscribers` - Newsletter subscribers

## Notes

- The application uses JPA with `spring.jpa.hibernate.ddl-auto=update`
- Tables will be auto-created/updated when you run the application
- You can run the SQL files manually or let Spring Boot handle it automatically

