# Ticket System - Spring Boot Application

This is a simple Spring Boot application that manages train stations with GET and POST requests to the `/api/trainstations` endpoint.

## Getting Started

Follow the steps below to set up and run the application.

## Prerequisites

- **JDK 11 or later**: Ensure that you have JDK 11 or a later version installed.
- **MySQL**: Install MySQL and MySQL Workbench on your machine.
- **Git**: Make sure you have Git installed to clone the repository.

## Clone the Repository

First, clone the GitHub repository to your local machine:

```bash
git clone https://github.com/praveen-saharan/Backend.git
```

## Set Up MySQL Database

1. Open MySQL Workbench.
2. Create a new database named `newdb` by running the following SQL commands:

```sql
create database newdb;
use newdb;
```

3. Update the database credentials in the `application.properties` file of the project to match your MySQL configuration.

## Update application.properties

Navigate to the `src/main/resources/application.properties` file and make sure your configuration matches the following:

```properties
spring.application.name=TicketSystem
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/newdb
spring.datasource.username=root
spring.datasource.password=pluralsight
spring.jpa.hibernate.ddl-auto=update
# spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Make sure to update the `spring.datasource.username` and `spring.datasource.password` with your MySQL credentials if different from `root` and `pluralsight`.

## Build and Run the Application

1. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
2. Navigate to `Ticket/src/main/java/com/backend/Ticket/TicketSystemApplication.java`.
3. Run the `TicketSystemApplication.java` class (right-click and select "Run").

This will start the Spring Boot application on `http://localhost:8080`.

## API Endpoints

- **GET /api/trainstations**: Retrieve the list of all train stations.
- **POST /api/trainstations**: Create a new train station.

Use the following JSON body in your POST request:

```json
{
    "stationName": "West Station",
    "stopNumber": 4,
    "fareAmount": 18.50
}
```

You can use a tool like Postman or cURL to interact with these endpoints.

### Example cURL for POST request:

```bash
curl -X POST http://localhost:8080/api/trainstations \
-H "Content-Type: application/json" \
-d '{
    "stationName": "West Station",
    "stopNumber": 4,
    "fareAmount": 18.50
}'
```

## Troubleshooting

- Ensure your MySQL server is running and accessible at `localhost:3306`.
- Make sure the MySQL username and password in `application.properties` are correct.
- If there are issues with database connection, verify that you can connect to `newdb` through MySQL Workbench with the same credentials.

## Conclusion

Once you have completed the steps above, your Spring Boot application should be running and connected to the MySQL database. You can start sending GET and POST requests to the API as needed.
