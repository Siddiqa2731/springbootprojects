Customer Management Application
This Spring Boot application provides CRUD (Create, Read, Update, Delete) operations for managing customer records in a database.

Features:
_________
Create: Add new customer records with details such as name, age, gender, and password.
Read: Retrieve customer details by ID, or fetch all customers.
Update: Modify customer password details.
Delete: Remove customer records by ID.

Technologies Used:
_________________
Java
Spring Boot
Spring Data JPA - Hibernate
MySQL (as database)
Maven (as build tool)

Setup:
Database Setup:
_______________
Ensure MySQL is installed and running.
Create a new database named my_database (or update application.properties if using a different name).
Execute the provided SQL script (data.sql) to create the necessary customer table.

Application Configuration:
_________________________

Clone the repository to your local machine.

Build and Run:
_______________
Navigate to the project directory.
Build the project using Maven:
mvn clean package

Run the application:
java -jar target/demo-0.0.1-SNAPSHOT.jar

Accessing the Application:
The application will run on http://localhost:8080.

Usage:
______
Endpoints
GET /customer/getAll: Fetch all customers.
GET /customer/id/{id}: Fetch customer by ID.
GET /customer/name?custName={customer_name}: Fetch customer by Name.
POST /customer/createCustomer: Create a new customer record.
PUT //customer/updatePassword/{id}: Update customer details (including password).
DELETE /customer/deleteAccount/{id}: Delete customer by ID.
Postman Collection

For detailed examples and testing, import the provided Postman collection.

