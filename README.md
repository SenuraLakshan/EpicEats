# EpicEats
EpicEats is envisioned as a cutting-edge platform that allows users to effortlessly locate restaurants, explore diverse menus, and share their culinary experiences through photos and reviews. This application will be designed following a microservices architecture to ensure scalability, flexibility, and a seamless user experience.  

https://miro.com/app/board/uXjVKDcaBP8=/

# Epic Eats: Restaurant Management System Based on Microservices

## Introduction
The Restaurant Management Microservices Project is an advanced process to manage a restaurant business through a network of different microservices. The major services include:
- Auth service for log-in
- Restaurant location service for creating, storing, and retrieving restaurant details
- Menu management service for handling restaurant menus
- User content service for user-generated content
- Reservation management service for booking tables

The implementation of utility and authentication services maintains security and enhances the overall management experience.

## Architecture

### Architecture Diagram
![Architecture Diagram](path/to/architecture_diagram.png)

### Design Decisions
This architecture follows the microservices pattern, offering scalability, maintainability, and ease of deployment. Below are the design decisions for partitioning the application into different services and their contributions:

- **API Gateway**
  - **Role:** Acts as a single-entry point for all client requests, providing load balancing, authentication, logging, and rate limiting.
  - **Contribution:** Simplifies client interactions and enhances security.

- **Discovery Server**
  - **Role:** Enables service discovery and registration using Spring Cloud Netflix Eureka.
  - **Contribution:** Facilitates dynamic service registration and discovery, ensuring services can find each other without hardcoding locations.

- **Menu Management Service**
  - **Role:** Handles menu operations such as creation, updating, and deletion.
  - **Contribution:** Maintains menu logic and updates without dependency on other services.

- **Menu Search Service**
  - **Role:** Provides search functionality for menu entries based on selected criteria.
  - **Contribution:** Enhances user experience with effective and meaningful menu searches.

- **Item Details Service**
  - **Role:** Maintains detailed information about each menu item, including ingredients, nutritional information, and price.
  - **Contribution:** Provides detailed menu item information, aiding in informed decision-making.

- **User Content Service**
  - **Role:** Manages user-generated content like reviews and ratings.
  - **Contribution:** Supports user engagement and feedback to improve service quality.

- **Restaurant Search Service**
  - **Role:** Enables users to search for restaurants based on location, cuisine, and other filters.
  - **Contribution:** Offers easy restaurant recommendations matching user preferences.

- **Restaurant Management Service**
  - **Role:** Manages restaurant data and operations, including adding new restaurants and updating details.
  - **Contribution:** Centralizes restaurant logic and operations, simplifying data management.

- **Databases**
  - **Role:** Stores data specific to each service, such as menu details and restaurant data.
  - **Contribution:** Ensures data consistency and performance, allowing each service to scale independently.

- **Firebase Authentication**
  - **Role:** Provides authentication services and user management.
  - **Contribution:** Secures system access, manages user authentication, and supports various authentication methods like email/password and social logins.

## Microservices

### Implementation Methods
The tech stack includes:
- Spring Firebase
- Java Spring Boot
- MySQL
- HTML
- Angular
- JavaScript
- TypeScript

Using Spring Cloud Gateway for API routing and Netflix Eureka for service discovery, the microservices are implemented.

### Core Services

#### Menu Management Service
- **Tech Stack:** Spring Boot, MySQL
- **Functionality:** Handles all menu operations.
- **REST API Endpoints:**
  - `GET /api/menus` - Retrieve menus
  - `POST /api/menus` - Create menus
  - `PUT /api/menus/{menuId}` - Update menus
  - `DELETE /api/menus/{id}` - Delete menus
  - `GET /api/menus/{menuId}/items` - Retrieve menu items
  - `POST /api/menus/{menuId}/items` - Create menu items
  - `PUT /api/menus/items/{Id}` - Update menu items
  - `DELETE /api/menus/items/{id}` - Delete menu items
  - `GET /api/menus/restaurants/{restaurantId}` - Retrieve menus by restaurant
  - `POST /api/menus/restaurants/{restaurantId}/menus` - Create menus by restaurant
  - `PUT /api/menus/restaurants/{restaurantId}/menus/{menuId}` - Update menus by restaurant
  - `DELETE /api/menus/restaurants/{restaurantId}/menus/{menuId}` - Delete menus by restaurant
- **Inter Service Interactions:** Interacts with menu search and restaurant services.

#### Menu Search Service
- **Tech Stack:** Spring Boot, MySQL
- **Functionality:** Provides efficient search capabilities across menu items.
- **REST API Endpoints:**
  - `GET /find/menus/search/restaurantId={restaurantId}&name={name}` - Retrieve menus by restaurant ID and name
  - `GET /find/menus/search/by-name-and-price?name={name}&price={price}` - Retrieve menu items by name and price
  - `GET /find/menus/search/by-price/price={price}` - Retrieve menu items by price
  - `GET /find/menus/search/by-name/name={name}` - Retrieve menu items by name
- **Inter Service Interactions:** Interacts with menu management service.

#### Item Details Service

#### User Content Service

#### Restaurant Search Service

#### Restaurant Management Service

#### Discovery Server

#### API Gateway

### Interservice Communications
Interservice communications are done using Rest Templates. Example:

```java
ResponseEntity<Map> response = restTemplate.exchange(
    "http://restaurant-service/api/restaurants/{restaurantId}" + menu.getRestaurantId(), 
    HttpMethod.GET, 
    null, 
    Map.class
);
