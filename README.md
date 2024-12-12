# Ticketing System

## Introduction
The Ticketing System is a real-time application for managing tickets. It includes a backend built with Spring Boot and a frontend using Angular. The system supports ticket creation, purchase, and logging with real-time updates via WebSocket.

## Setup Instructions

### Prerequisites
Ensure you have the following installed:

#### Backend
- Java 17 or later
- Maven 3.8+
- PostgreSQL 14.x or later

#### Frontend
- Node.js 18.x or later
- Angular CLI 15.x or later

### Steps to Build and Run the Application

#### Backend (Spring Boot)
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <repository-backend-folder>
   ```
2. Configure the PostgreSQL database:
   - Create a PostgreSQL database (e.g., `ticketing_system`).
   - Update `application.properties` or `application.yml` with your database details:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/ticketing_system
     spring.datasource.username=<your-username>
     spring.datasource.password=<your-password>
     spring.jpa.hibernate.ddl-auto=update
     ```

3. Run the Spring Boot application:

   The backend server will start at `http://localhost:8080`.

#### Frontend (Angular)
1. Navigate to the frontend folder:
   ```bash
   cd <repository-frontend-folder>
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the Angular development server:
   ```bash
   ng serve
   ```
   The frontend application will be available at `http://localhost:4200`.

### CLI Usage
When running the Command Line Interface (CLI):
- Ensure the backend server is running, as the CLI interacts with the backend to store information in the PostgreSQL database.

## Usage Instructions

### Configuring the System
1. Open the frontend application in your browser (`http://localhost:4200`).
2. Fill out the configuration form with:
   - **Total Tickets**: Total number of tickets.
   - **Ticket Release Rate**: Vendor ticket release frequency (in seconds).
   - **Customer Retrieval Rate**: Customer ticket purchase frequency (in seconds).
   - **Max Ticket Capacity**: Maximum ticket pool capacity.
3. Click **Submit** to apply the configuration and start the system.

### Using the UI Controls

#### Configuration Form
- **Submit**: Saves the configuration and starts the system.

#### Log Display
- Shows real-time ticket transaction logs.

#### Control Panel
- **Start**: Starts log streaming.
- **Pause/Resume**: Pauses or resumes ticketing.
- **Stop**: Stops the system and clears logs.

### Real-Time Updates
- Logs are updated in real-time via WebSocket.

## Support
For any issues, contact the project maintainers or refer to the repository's documentation and issue tracker.

