# E-Commerce Website 

A full-stack e-commerce web application built with **Java Spring Boot**, **Spring MVC**, **Thymeleaf** and **MySQL**.
The storefront and the admin dashboard are fully **Arabic (RTL)** and are designed for a store that sells scales (موازين).

---

## Features

### Storefront
- Home page with the product catalog, categories, advertisements and active offers
- Product details page with up to three images, price, discount percentage and stock
- Category browsing and live product search (`GET /api/search`)
- Shopping cart: add item, increase / decrease quantity, remove item, clear cart
- Checkout and order placement with an order confirmation page
- User registration and login with BCrypt password hashing
- Static pages: about, contact, privacy, shipping and return policy

### Admin Dashboard (`/admin/**` — `ADMIN` role only)
- Product management: add, edit and delete products, product image upload, pricing and stock
- Category management
- Offers management (add / delete) and advertisements management (add / toggle / delete)
- Order management: list all orders and update the order status
- User management

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.5.7 (Spring MVC) |
| View layer | Thymeleaf, Thymeleaf Extras Spring Security 6, HTML/CSS/JS |
| Security | Spring Security 6 — form login, BCrypt, role-based access (`ADMIN` / `CUSTOMER`) |
| Persistence | Spring Data JPA / Hibernate |
| Database | MySQL 8 |
| Build tool | Maven (WAR packaging) |
| Other | Lombok, Spring Boot DevTools |

---

## Project Structure

```
src/main/java/com/kalyan/ecommers_website/
├── config/        # SecurityConfig, WebConfig
├── controller/    # MVC controllers (storefront + admin) and one REST controller
├── model/         # JPA entities and DTOs
├── repository/    # Spring Data JPA repositories
└── service/       # Business logic

src/main/resources/
├── templates/     # Thymeleaf views (storefront, admin, fragments)
├── static/        # CSS, JavaScript and image assets
└── application.properties
```

**Main entities:** `User`, `Role`, `Product`, `Category`, `Cart`, `CartItem`, `Order`, `OrderItem`, `OrderStatus`, `Offer`, `Advertisement`.

---

## Getting Started

### Prerequisites
- JDK 21
- Maven 3.9+
- MySQL 8

### 1. Create the database
```sql
CREATE DATABASE ecommers CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. Configure the database connection
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommers?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```
Hibernate creates and updates the tables automatically (`spring.jpa.hibernate.ddl-auto=update`).

### 3. Run the application
```bash
mvn spring-boot:run
```
The application starts on **http://localhost:9192**

### 4. Build the deployable WAR
```bash
mvn clean package -DskipTests
```
The artifact is generated at `target/ecommers_website-0.0.1-SNAPSHOT.war` and can be deployed on Apache Tomcat.

---

## Application URLs

| URL | Description | Access |
|---|---|---|
| `/` | Home page | Public |
| `/product/{id}` | Product details | Public |
| `/category/{id}` | Products of a category | Public |
| `/cart` | Shopping cart | Public |
| `/cart/checkout` | Checkout | Public |
| `/login`, `/register` | Login / registration | Public |
| `/about`, `/contact`, `/privacy`, `/shipping`, `/return` | Static pages | Public |
| `/api/search` | Product search (JSON) | Public |
| `/admin/products` | Product management | ADMIN |
| `/admin/orders` | Order management | ADMIN |
| `/admin/users` | User management | ADMIN |
| `/admin/offers`, `/admin/advertisements` | Offers & advertisements | ADMIN |

---

## Author

**Remon Kaheel** — [GitHub](https://github.com/Remonkaheel)
