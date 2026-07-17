# URL Shortener

## Overview
A RESTful URL Shortener built using Java Spring Boot and PostgreSQL.

## Features
- Shorten long URLs
- Redirect to original URL
- Click tracking
- URL details API
- Delete shortened URLs
- Duplicate short-code prevention

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven

## Architecture
Controller
↓
Service
↓
Repository
↓
PostgreSQL

## API Endpoints

POST /api/urls
GET /{shortCode}
GET /api/urls/details/{shortCode}
DELETE /api/urls/{shortCode}

## How to Run

1. Clone repo
2. Configure PostgreSQL
3. Run Maven
4. Start Spring Boot

## Future Improvements

- Custom aliases
- Swagger
- Expiring URLs
- Authentication
