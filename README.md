# Calculator REST API (Java + Spring Boot)

This is a simple RESTful API that performs addition on a list of numbers passed via query parameters.

## Features

- Add any number of comma-separated numeric operands
- Returns the sum in JSON format
- Handles invalid input with clear error responses
- Unit tests

## Example Requests

**Add:**
GET /calculator/add?operands=1,2,3
Response: { "sum": 6.0 }

**Invalid input:**
GET /calculator/add?operands=1,a
Response: { "error": "Invalid input: all operands must be numbers." }

**Empty(or spaces) input:**
GET /calculator/add?operands=
Response: { "sum": 0.0 }

## How to Run

### Requirements
- Java 8+ (installed and in your PATH)
- Spring Boot 2.7
- Maven 3.9 (in your PATH)

### Steps

1. Clone the repo:
- git clone https://github.com/RamiFerjaniDev/calculator-api.git
- cd calculator-api
2. Run the application:
  
```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

3. Access the API:
- In your browser or tool (Postman):

  ```
  http://localhost:8080/calculator/add?operands=5,6
  ```

### To Run Tests
Linux
```bash
./mvnw test
```

Windows
```bash
mvn test
```

### Notes
- Make sure you run these commands from the project root directory where the pom.xml file is located.

- To stop the running app, press Ctrl+C in the terminal.
