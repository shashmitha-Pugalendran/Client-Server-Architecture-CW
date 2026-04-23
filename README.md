Module Name: Client-Server Architectures Coursework  
Module Code: 5COSC022W  
Description: RESTAPI design,development and Implementation   
Due Date: 24th April 2026, 13:00  
Student Name: Shashmitha Pugalendran  
Student ID: 20231510/w2120214  

<ins>**An Overview of the API design.**</ins>

This API is designed following REST principles to manage a Smart Campus system. It models three main resources: Rooms, Sensors, and Sensor Readings. Each resource is represented using clear and hierarchical endpoints. Rooms act as the top-level resource, sensors are linked to rooms through a roomId, and sensor readings are implemented as a sub-resource under sensors. This structure reflects real-world relationships and improves clarity.

The API uses standard HTTP methods such as GET for retrieval, POST for creation, and DELETE for removal. It also uses meaningful HTTP status codes such as 200, 201, 404, 409, 422, and 403 to communicate results. Error handling is implemented using exception mappers to ensure clean and consistent responses. Data is stored in-memory using maps, which allows fast access and simple implementation.

Technologies Used for implementing:
1. Java
2. JAX-RS
3. Jersey
4. Apache Tomcat
5. Maven
6. Postman

<ins>**Instructions on how to build the project and launch the server.**</ins>

1. Open the project in NetBeans.
2. Ensure Apache Tomcat is installed and configured in NetBeans.
3. Right click the project and select Clean and Build and check if there are no errors.
4. Right click the project again and select Run and check if it runs without errors.
5. Wait until the server starts successfully.
6. Open a browser or Postman to test use the following URL: http://localhost:8080/CW_Code_CSA/api
7. The API is now running successfully.

<ins>**Sample Curl Commands.**</ins>
1. Creating a Room.  
   curl -X POST http://localhost:8080/CW_Code_CSA/api/rooms \
-H "Content-Type: application/json" \
-d '{"id":"R1","name":"Computer Lab","capacity":40}'

2. Getting Rooms.  
   curl http://localhost:8080/CW_Code_CSA/api/rooms

3. Creating a Sensor.  
curl -X POST http://localhost:8080/CW_Code_CSA/api/sensors \
-H "Content-Type: application/json" \
-d '{"id":"S1","type":"Temperature","status":"ACTIVE","currentValue":0,"roomId":"R1"}'

4. Getting Sensors.  
curl http://localhost:8080/CW_Code_CSA/api/sensors

5. Adding a Sensor Reading to a Sensor.  
curl -X POST http://localhost:8080/CW_Code_CSA/api/sensors/S1/readings \
-H "Content-Type: application/json" \
-d '{"id":"READ1","timestamp":1710000000,"value":25.5}'

6. Get Sensor Readings from a Sensor.  
curl http://localhost:8080/CW_Code_CSA/api/sensors/S1/readings

7. Deleting a Room (This will fail if room contains sensors)
curl -X DELETE http://localhost:8080/CW_Code_CSA/api/rooms/R1

<ins>**Coursework Tasks Answers.**</ins>  

<ins>Part 1: Service Architecture & Setup.</ins>

1. Project & Application Configuration.  
   JAX-RS creates a new instance of a resource class for each incoming HTTP request by default rather than as a singleton, which means each API call gets its own object and doesn't have a shared state inside the resource class. This ensures that resource classes remain stateless and thread-safe by design. Moving onto the impact on data, because instances are not shared, any data stored within instance variables is lost after the request completes;Therefore, you must store shared data in static structures like hashmaps in this project this can be found inside the com.campus.service package as a java class named DataStorage. Since multiple requests can access and modify these shared structures concurrently, this design introduces the risk of race conditions and data inconsistency, so proper synchronization or the use of thread-safe collections is necessary to ensure data integrity.
   
2. The "Discovery" Endpoint.  
   Hypermedia is considered a hallmark of advanced RESTful design, because it allows the API to return links to other endpoints.
   For Example: "rooms": "/api/v1/rooms", This approach benefits client developers more, compared to static documentation by the following ways,
   firstly, The client does not need hardcoded URLs, it can simply follow the links returned by the server to navigate the system. This makes the 
This makes the API easier to use and more flexible and reduces dependency on documentation and helps developers interact with the API in a more intuitive and reliable way.

<ins>Part 2: Room Management.</ins>

1. Room Resource Implementation.  
   Returning only IDs results in smaller responses and faster data transfer, but it requires the client to make additional requests to retrieve full details. On the other hand, returning full objects provides all relevant information in a single response, making it easier for the client to use, although it increases bandwidth usage. Therefore, returning full objects improves usability, while returning only IDs is more efficient for performance.

2. Room Deletion & Safety Logic.  
   Yes, the "DELETE" operation is idempotent because performing it multiple time will result in the same system state. For example, the first DELETE request removes the room, and any subsequent DELETE requests will not change the system further since the room no longer exists. This means repeated calls will not produce additional effects therefore it is idempotent.

<ins>Part 3: Sensor Operations & Linking.</ins>

1. Sensor Resource & Integrity.  
   The @Consumes(MediaType.APPLICATION_JSON) annotation ensures that the API only accepts JSON data in requests. If a client sends data in another format such as plain text or xml, JAX-RS will reject the request and return a 415 Unsupported Media Type error. This happens because the server is configured to process only JSON and cannot interpret other unsupported formats.

2. Filtered Retrieval & Search.  
   Using query parameters such as /api/v1/sensors?type=CO2 is ideal for filtering because they are optional, flexible, and allow multiple filters to be combined in a single request. In contrast to, embedding filters in the path such as /api/v1/sensors/type/CO2 makes the API less flexible and harder to extend. Query parameters provide a cleaner design and are better suited for searching and filtering operations.

<ins>Part 4: Deep Nesting with Sub- Resources.</ins>

1. The Sub-Resource Locator Pattern.  
   The Sub-resource locator pattern helps organize the API by separating logic into smaller, focused classes. Instead of handling all nested paths in a single large controller, each resource is assigned to its own class, which improves readability, maintainability, and scalability. This makes the API easier to manage as it grows in complexity.

2. Historical Data Management.  
   When a new reading is added, the system not only stores the reading but also updates the current Value of the corresponding sensor. This ensures that the latest sensor value is always available without needing additional queries. It maintains data consistency between the sensor and its readings.

<ins>Part 5: Advanced Error Handling & Exception Mapping.</ins>  

2. Dependency Validation (422 Unprocessable Entity).   
   When an operation cannot be finished because of the system's current state, a 409 Conflict response is displayed. In this instance, trying to remove a room that still has sensors assigned to it causes a dispute because doing so would leave the sensors orphaned. As a result, the request is blocked and a 409 status is returned by the system.

&nbsp;&nbsp;&nbsp;4\. The Global Safety Net (500).  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Exposing internal stack traces is a security risk because it can reveal sensitive information such as class names, file structures, &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;and application logic. Attackers can use this information to identify vulnerabilities and exploit the system. To prevent this, the &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;API returns a generic 500 Internal Server Error message instead of exposing internal details.
   

   
   
   
 







  
   
   
   
   

