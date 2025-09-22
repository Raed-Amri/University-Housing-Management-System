# ✈️ University Housing Management System

---

## 📌 Overview
This is a **Spring Boot-based application** designed to streamline the management of university housing.  
The system models core entities such as **universities, foyers, blocs, rooms, students, and reservations**, utilizing **Spring Data JPA** for robust data persistence.  

It supports **CRUD operations** across these entities and defines relationships, such as **foyers assigned to universities** and **students linked to reservations**, to ensure efficient housing assignments.

> The project is structured as a **backend application**, with potential for extension into a **web API** or full **MVC application** to enhance usability.

---

## 🎯 Project Goal
The primary objective is to simulate a real-world university housing management system that enables:  
- Efficient management of **universities, foyers, blocs, rooms, and student reservations**.  
- Tracking of **student details and reservation statuses**.  
- Practical application of **Spring Data JPA relationships, CRUD operations, and database management** in a Java environment.  
- Serving as a foundation for **scaling into a comprehensive web-based housing service**.

---

## 🚀 Features
- **University Management:** Create and manage universities with names and addresses.  
- **Foyer Management:** Assign a single foyer to each university with a defined capacity.  
- **Bloc Management:** Administer blocs within a foyer, each with its own capacity.  
- **Room Management:** Define rooms by number and type (**SIMPLE, DOUBLE, TRIPLE**).  
- **Student Management:** Handle student information including name, CIN, school, and date of birth.  
- **Reservation System:** Facilitate room reservations for an academic year, with validation options.

---

## 🛠️ Tech Stack
- **Language:** Java 17+  
- **Framework:** Spring Boot (Data JPA, Web, Validation)  
- **ORM:** Hibernate  
- **Database:** MySQL / PostgreSQL (configurable)  
- **Dependencies:**  
  - Spring Data JPA  
  - Hibernate  
  - Lombok (reduces boilerplate code)  
  - Maven (dependency management)

---

## 📁 Project Structure
The application is organized into the following core entities:

- **Université:** idUniversite (long), nomUniversite (String), adresse (String).  
- **Foyer:** idFoyer (long), nomFoyer (String), capaciteFoyer (long), linked to one university.  
- **Bloc:** idBloc (long), nomBloc (String), capaciteBloc (long), associated with one foyer.  
- **Chambre:** idChambre (long), numeroChambre (long), typeC (TypeChambre enum: SIMPLE, DOUBLE, TRIPLE).  
- **Réservation:** idReservation (String), anneeUniversitaire (Date), estValide (boolean), linked to one room and student.  
- **Étudiant:** idEtudiant (long), nomEt (String), prenomEt (String), cin (long), ecole (String), dateNaissance (Date).

---

## 📊 Class Diagram
- The system’s architecture is based on the following relationships:
- Université → 1 Foyer
- Foyer → * Blocs
- Bloc → * Chambres
- Chambre → * Réservations
- Étudiant → * Réservations


---

## ⚙️ Getting Started

### Prerequisites
- **Java 17+ SDK** or later  
- **Maven** (for dependency management)  
- **IDE:** IntelliJ IDEA, Eclipse, or VS Code with Java extensions  
- **Database:** MySQL or PostgreSQL (configured as needed)




