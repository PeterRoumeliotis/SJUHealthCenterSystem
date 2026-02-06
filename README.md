# SJU Health Center System

A Java desktop application for managing patient healthcare records, appointments, and billing at Saint John's University Health Center. The system provides separate interfaces for patients and healthcare employees with role-based access control.

## Features

### Patient Portal
- Register with personal and insurance information
- Log in using X Number and password
- View personal medical records (demographics, height, weight, DOB, insurance)
- Schedule and manage appointments
- View billing and charges
- Access health center contact information

### Employee Portal
- Log in using staff credentials
- View assigned appointments
- Add and view patient records
- Assign and manage billing information

## Tech Stack

- **Language:** Java
- **GUI:** Java Swing
- **Database:** MySQL 8.0
- **Connectivity:** JDBC

## Project Structure

```
SJUHealthCenterSystem-main/
├── Start.java                      # Application entry point / splash screen
├── PatientLogin.java               # Patient login UI
├── EmployeeLogin.java              # Employee login UI
├── PatientLoginAuthenticator.java  # Patient credential verification
├── EmployeeLoginAuthenticator.java # Employee credential verification
├── PatientDashboard.java           # Patient main menu
├── EmployeeDashboard.java          # Employee main menu
├── RegisterPatient.java            # Patient registration form
├── AppointmentSystem.java          # Appointment scheduling
├── showPatientRecords.java         # Medical records viewer
├── showBillingInformation.java     # Billing/charges viewer
├── InformationDisplay.java         # Health center contact info
├── HealthServiceDB.sql             # Database schema and sample data
├── SJUHealthCenterDB               # Users table schema
├── Mockdata.sql                    # Sample user data
├── sjulogo.png                     # SJU logo assets
├── sjuimage.jpg
├── sju.png
└── sjulogolong.png
```

## Database Schema

The application uses a MySQL database named `sjuhealthservices` with the following tables:

| Table | Description |
|-------|-------------|
| `patient` | Patient accounts (X Number, name, credentials, role) |
| `patientrecord` | Health records (DOB, height, weight, insurance details) |
| `appointment` | Scheduled appointments (patient, employee, date, time) |
| `employee` | Staff accounts (name, credentials, role) |
| `billinginfo` | Patient billing and insurance records |
| `medication` | Patient medication assignments |

## Setup

### Prerequisites
- Java JDK 8 or higher
- MySQL Server 8.0+

### Database Setup
1. Start your MySQL server.
2. Import the schema and sample data:
   ```bash
   mysql -u root -p < HealthServiceDB.sql
   ```
3. Optionally load additional mock data:
   ```bash
   mysql -u root -p sjuhealthservices < Mockdata.sql
   ```

### Configuration
Update the database connection credentials in the Java source files if your MySQL setup differs from the defaults:
- **URL:** `jdbc:mysql://localhost:3306/sjuhealthservices`
- **User:** `root`
- **Password:** *(set to match your MySQL root password)*

### Running the Application
```bash
javac *.java
java Start
```

## Sample Credentials

| Role | Username | Password |
|------|----------|----------|
| Patient | X306 | SJU |
| Employee | umargul1122 | sjuhealth1122 |
