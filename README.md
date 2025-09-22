🏫 University Housing Management System

This project is a Spring Boot application for managing university housing, including universities, foyers, blocs, rooms, students, and reservations. It provides a backend system to handle student housing assignments and reservations efficiently.

📌 Features

Université Management:
Create and manage universities with their names and addresses.

Foyer Management:
Assign one foyer to each university with a defined capacity.

Bloc Management:
Manage blocs within a foyer, each with its own capacity.

Chambre Management:
Define rooms with numbers and types:

SIMPLE

DOUBLE

TRIPLE

Étudiant Management:
Manage student information including name, CIN, school, and date of birth.

Réservation System:
Students can reserve rooms for an academic year.
Each reservation can be validated or not.

📊 Class Diagram

The system is based on the following class structure:

Université → 1 Foyer

Foyer → * Blocs

Bloc → * Chambres

Chambre → * Réservations

Étudiant → * Réservations

🛠️ Technologies Used

Java 17+

Spring Boot (Data JPA, Web, Validation)

Hibernate ORM

MySQL / PostgreSQL (configurable)

Lombok for reducing boilerplate code

Maven for dependency management
