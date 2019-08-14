# Uber Service location

first compile

`mvn clean compile package`

then execute

`java -jar target/uber-service-location-0.0.1-SNAPSHOT.jar`

got to `htt://localhost:8993/uber-gps-location-request/list` to list all the services

This service provides the following methods:

### /uber-gps-location-request/list
That lists all the gps location requests

### /uber-gps-location-request/add
That allows admin to request cars locations via gps

### /uber-driver-notification/find
That allows admin to check for the status of their location requests