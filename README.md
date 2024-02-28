# Git User Details Management System

This program provides a simple command-line interface, and 2 micro-services to manage details of Git users in a database.

## Prerequisites

- Go installed on your system.
- Kafka broker running on `localhost:9092`.
- Sarama-v1 Go package installed (`go get -u gopkg.in/Shopify/sarama.v1`).
- Java JDK installed on your system.
- Apache Maven for building the project.
- PostgreSQL database configured.
- Kafka broker running on your system.

## Installation

1. Clone this repository.
2. Navigate to the project directory.
3. Run `go build` to build the executable.
4. Navigate to the project directory.
5. Build the project using Maven: `mvn clean install`.

## Usage

- Run the executable generated after building.
- You will be presented with a menu to perform the following actions:
  1. Add details of GIT users to DATABASE.
  2. Display all details.
  3. Delete details of git users.
- Run the generated JAR file using `java -jar <jar_filename>.jar`.
- The microservice will start on a predefined port (usually 8080 by default).
- You can then access the following endpoints:

## Technologies Used

- Spring Boot
- Spring Data JPA
- Spring Kafka
- PostgreSQL
- Gson (Google JSON library)

## Features

- Adding Git user details to a Kafka topic for storage.
- Deleting Git user details from a Kafka topic.

### Get Github User Details
- **URL:** `/users/{name}`
- **Method:** `GET`
- **Description:** Retrieves details of a Github user by username.

### Get All Github User Details
- **URL:** `/users/data`
- **Method:** `GET`
- **Description:** Retrieves details of all Github users.

### Get Specific Github User Details
- **URL:** `/users/data/{id}`
- **Method:** `GET`
- **Description:** Retrieves details of a specific Github user by ID.

### Delete Specific Github User Details
- **URL:** `/users/data/{id}`
- **Method:** `DELETE`
- **Description:** Deletes details of a specific Github user by ID.

## Configuration

Ensure that you have configured the following properties in your `application.properties` file:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password
```

## Kafka Configuration
spring.kafka.bootstrap-servers=localhost:9092

## Contributing

Contributions are welcome! If you have any ideas, enhancements, or bug fixes, feel free to open an issue or submit a pull request.