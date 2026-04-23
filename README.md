Module Name: Client-Server Architectures Coursework  
Module Code: 5COSC022W  
Description: RESTAPI design,development and Implementation   
Due Date: 24th April 2026, 13:00  
Student Name: Shashmitha Pugalendran  
Student ID: 20231510/w2120214  

Coursework Tasks Answers.
Part 1: Service Architecture & Setup. 
1. Project & Application Configuration.
   JAX-RS creates a new instance of a resource class for each incoming HTTP request by default rather than as a singleton, which means each API call gets its own object and doesn't have a shared state inside the resource class. This ensures that resource classes remain stateless and thread-safe by design. Moving onto the impact on data, because instances are not shared, any data stored within instance variables is lost after the request completes;Therefore, you must store shared data in static structures like hashmaps in this project this can be found inside the com.campus.service package as a java class named DataStorage. Since multiple requests can access and modify these shared structures concurrently, this design introduces the risk of race conditions and data inconsistency, so proper synchronization or the use of thread-safe collections is necessary to ensure data integrity.
   
2.

