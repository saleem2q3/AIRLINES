AIRLINES Flight Booking System

AIRLINES is a full-stack web application for booking flights. Users can search flights, view details, and complete reservations in a seamless and user-friendly interface. The backend handles complex logic for managing flight schedules, customer bookings, and payments, while the frontend provides a clean, intuitive experience.

Features
   . User registration and login
   
   . Flight search and filter
   
   . Booking flights with seat selection
   
   . Admin panel for managing flights and bookings
   
   . Real-time flight updates
   
Technologies Used
   . Frontend: JSF, HTML, CSS, JavaScript, Bootstrap
   
   . Backend: Java, EJB, JSF
   
   . Database: MySQL
   
   . Server: Red Hat JBoss
   
   . Version Control: Git
   
   . Project Architecture
   
Model
   The model in this project represents the core data of the application, including flight details, users, and bookings. It follows the MVC (Model-View-Controller) architecture and interacts with the database 
   through EJB.

   . Flight.java: Represents flight data (e.g., flight number, destination, price).
   
   . User.java: Manages user information (e.g., username, email, password).
   
   .Booking.java: Handles user bookings for specific flights.
   
Controller

   The controller layer manages user interactions with the system and maps them to the business logic.

  . FlightController.java: Handles flight search, booking, and cancellation requests.
  
  . UserController.java: Manages user login, registration, and profile operations.
  
  . The controllers use JSF ManagedBeans to connect the view with the model and entity classes.

Entity

  The entity classes represent the database structure, mapping Java objects to database tables. These are EJB components annotated with @Entity.

  . FlightEntity.java: Maps to the flights table in MySQL.
  
  . UserEntity.java: Maps to the users table.
  
  . BookingEntity.java: Manages bookings for flights.

Converter
  . The converters in the project are responsible for transforming data between UI elements and model entities.

  . FlightConverter.java: Converts flight information between the front-end and back-end systems.
  
  . UserConverter.java: Handles data conversion for user inputs.
  
Installation
Prerequisites
  .Java JDK 8+
  .MySQL installed and running
  .Maven for dependency management
  .JBoss server (on Red Hat)

STEPS

  1.Clone the repository:
  
       git clone https://github.com/saleem2q3/AIRLINES.git

  2.Set up the MySQL database:

   .Create a database named airlines_db
   .Run the provided SQL scripts in the /sql folder to create the necessary tables.


  3.Configure the persistence.xml file in the src/main/resources/META-INF folder for your MySQL connection.

  4.Build the project using Maven:
  
      .mvn clean install
  5.Deploy the project on the JBoss server.

How to Run
  1.Start the JBoss server.
  
  2.Navigate to the project URL in your browser (e.g., http://localhost:8080/airlines).
  
  3.Register as a user or log in to search for flights and book tickets.

Future Improvements

  1.Implement real-time notifications for flight status changes.
  
  2.Add payment gateway integration for online transactions.
  
  3.Improve user interface with enhanced CSS animations.

  
