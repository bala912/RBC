# RBC

## Versions
- Spring boot 2.7.12 Snapshot ( https://start.spring.io/ )
- Java 11

## Application Architecture
- config
- controllers 
- models
- repositories 
- services
- utils
- exception


## Unit and Integration tests
- Unit and integration tests are implemented in /test folder
- Integration tests the controller end points and covers all the feature requirements including 404 Not Found, 400 Bad Request and Empty list cases

## Caching 
- Is implemented with 'org.springframework.boot:spring-boot-starter-cache' package
- ScheduleService queries the DB and the results are cached

## Exception handling
- Implemented with controllers/GlobalExceptionHadler
- Intercepts Application Exception and returns appropriate response back to client

## Future iterations 
- Add more functionality to manage Schedule (Add/Delete/Update) new Schedule
- Implement Authentication
- Query/filter by more parameters (id,arrival)