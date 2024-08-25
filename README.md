# E-Commerce Food Delivery Application

This repository contains a subscription-based e-commerce application focused on food delivery. The application is split into two main parts:
- Backend: A Java-based web application.
- Frontend: A simple user interface built using HTML, CSS, and JavaScript.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Backend Structure](#backend-structure)
- [Frontend Structure](#frontend-structure)
- [Usage](#usage)

## Project Overview

This project is a full-fledged e-commerce application that allows users to subscribe to various food delivery plans. The backend is built using Java, and the frontend is implemented using plain HTML, CSS, and JavaScript.

## Features

- User registration and authentication.
- Subscription management (create, update, delete subscriptions).
- Food delivery scheduling.
- Payment processing.
- Admin panel for managing users and subscriptions.
- Responsive UI for a seamless experience across devices.

## Technologies Used

### Backend
- Java (JDK 17)
- JDBC with MySQL
- Maven for dependency management
- JUnit 5 for testing

### Frontend
- HTML5
- CSS3
- JavaScript

## Getting Started

### Prerequisites

- Java 17 or later
- Maven
- MySQL
- A web server like Tomcat (for deployment)
- A modern web browser (for frontend)

### Setup Backend

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/your-repo-name.git
    ```

2. Navigate to the backend directory:
    ```bash
    cd your-repo-name
    ```

3. Set up the MySQL database:
    - Create a new database named `ecommerce_db`.
    - Update the `src/main/resources/application.properties` file with your MySQL username and password.

4. Build the project using Maven:
    ```bash
    mvn clean install
    ```

5. Deploy the application on a web server like Tomcat.

6. Run the application:
    ```bash
    mvn tomcat7:run
    ```

### Setup Frontend

1. Navigate to the frontend directory:
    ```bash
    cd your-repo-name/frontend
    ```

2. Open `index.html` in your web browser.

## Backend Structure

The backend follows a layered architecture. Below is an overview of the package structure:

- `in.co.hsbc.ecommerceApp`
  - `controller`: Contains all the REST controllers.
  - `service`: Contains business logic and interacts with DAOs.
  - `dao`: Data Access Objects for database interaction.
  - `entity`: Java entities mapping to database tables.
  - `exception`: Custom exception classes.
  - `config`: Configuration classes, like database configuration.

### Example Classes

- `Customer.java`: Represents the customer entity.
- `CustomerDao.java`: DAO interface for customer-related operations.
- `CustomerServiceImpl.java`: Implements business logic for customer-related operations.
- `CustomerController.java`: REST controller to handle customer-related requests.

## Frontend Structure

The frontend is straightforward and consists of the following files:

- `index.html`: The main landing page.
- `style.css`: Contains the styling for the application.
- `app.js`: Handles the client-side logic using vanilla JavaScript.
