# contactapp
**contactapp** simple contact list application

## Supported functionality
 - Listing people (name and photo)
 - Searching by name
 - Paging
 - Add/Edit/Remove

## Technology stack
 - Maven [3.3+](https://maven.apache.org)
 - Spring Boot [2.0.5](https://spring.io/projects/spring-boot)
 - Java [1.8+](http://www.oracle.com/technetwork/java/javase/overview/index.html)
 - Angular [7](https://angular.io)
 - Spring JPA/Hibernate
 - Spring Rest
 - H2 Database [latest](http://www.h2database.com)
 - Log4j2
 
## Build and Run
Build the project
 > ./mvnw clean install
 
This will build WEB and CORE sides of the project. You don't need to run every side independently, because all generated files from WEB side will be automatically copied to CORE. So you need only to go to CORE side and run it.
 > cd contactapp-core

Run the application (Maven)
 > ./mvnw spring-boot:run

Run the application (Java)
 > java -jar target/contactapp-core-1.0.jar
 
## Launch up H2 DB Console at 
http://localhost:8080/h2
```
JDBC URL: jdbc:h2:~/contactapp
User Name: sa
Password:
```