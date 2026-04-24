# w2121119-Smart-Campus-API

## Overview
This is a REST API for managing rooms, sensors, and sensor readings in a Smart Campus system.

## How to Run
1. Open the project in IntelliJ
2. Run Main.java
3. Open:
http://localhost:8080/api/v1/

## Sample curl commands

### Get all rooms
curl -X GET http://localhost:8080/api/v1/rooms

### Create a room
curl -X POST http://localhost:8080/api/v1/rooms \
-H "Content-Type: application/json" \
-d "{\"id\":\"LIB-301\",\"name\":\"Library Quite Study\",\"capacity\":40}"

### Create a sensor
curl -X POST http://localhost:8080/api/v1/sensors \
-H "Content-Type: application/json" \
-d "{\"id\":\"TEMP-001\",\"type\":\"Temperature\",\"status\":\"ACTIVE\",\"currentValue\":22.5,\"roomId\":\"LIB-301\"}"

### Add a reading
curl -X POST http://localhost:8080/api/v1/sensors/TEMP-001/readings \
-H "Content-Type: application/json" \
-d "{\"value\":25.5}"

###################
## Report Answers

### Part 1 ###

**Q1: JAX-RS Resource Lifecycle**
By default, JAX-RS resource classes follow a per-request lifecycle, meaning a new instance of the resource is created for each incoming HTTP request. This design helps to avoid issues related to shared state between requests. However, when shared data structures such as HashMaps are used, careful handling is required to ensure thread safety and prevent race conditions in concurrent environments.

**Q2: HATEOAS (Hypermedia as the Engine of Application State)**
HATEOAS enables clients to interact with a RESTful API dynamically by using hyperlinks provided within the responses. Instead of relying on fixed endpoint documentation, clients can discover available actions through the API responses themselves. This approach reduces coupling between the client and server and enhances the flexibility and evolvability of the system.

---

### Part 2 ###

**Q1: Returning IDs vs Full Objects**
Returning only identifiers can reduce network bandwidth consumption; however, it may require multiple additional requests from the client to retrieve complete resource details. In contrast, returning full objects decreases the number of required requests and can improve performance, although it increases the size of the response payload. The appropriate approach depends on system design considerations and performance requirements.

**Q2: DELETE Idempotency**
The HTTP DELETE method is considered idempotent because multiple identical requests produce the same result. Once a resource has been deleted, subsequent DELETE requests for the same resource do not alter the system state further.

---

### Part 3 ###

**Q1: @Consumes Annotation**
The @Consumes annotation specifies the media type that a resource method is capable of accepting. If a client sends data in a format that is not supported, the server responds with a 415 Unsupported Media Type error, ensuring that only valid and expected data formats are processed.

**Q2: QueryParam vs Path**
Query parameters are generally used for filtering, sorting, and providing optional criteria when retrieving collections of resources. In contrast, path parameters are used to identify specific resources. Therefore, query parameters are more appropriate for filtering operations, while path parameters are better suited for resource identification.
---

### Part 4 ###

**Q1: Sub-resource Locator Pattern**
The sub-resource locator pattern improves system modularity by delegating the handling of nested resources to separate resource classes. This approach reduces complexity within the main resource class and improves code maintainability. It also enhances scalability, particularly in large and complex RESTful APIs.

**Q2: Data Consistency**
When a new sensor reading is recorded, the system updates the sensor’s current value to reflect the most recent measurement. This ensures that the application consistently provides up-to-date information without requiring additional queries.

---

### Part 5 ###

**Q1: HTTP 422 vs 404**
The HTTP 422 Unprocessable Entity status code is used when a request is syntactically correct but semantically invalid, such as referencing a non-existent resource in a valid format. In contrast, the 404 Not Found status code indicates that the requested resource does not exist on the server.

**Q2: Stack Trace Security Risk**
Exposing stack traces in production environments presents a security risk, as they may reveal sensitive internal implementation details such as class names, file structures, and system paths. Such information can be exploited by attackers to identify potential vulnerabilities.

**Q3: Logging Filters**
Implementing logging through JAX-RS filters is more efficient than embedding logging statements within individual methods. Filters centralize logging logic, reduce code duplication, and ensure consistent logging behavior across all API endpoints.
