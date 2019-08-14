# Uber Driver Notification

first compile

`mvn clean compile package`

then execute

`java -jar target/uber-service-location-0.0.1-SNAPSHOT.jar`

got to `htt://localhost:8993/uber-driver-notification/list` to list all the notifications

This service provides the following methods:

### /uber-driver-notification/list
That lists all the notifications

### /uber-driver-notification/find
That allows a driver to look for new notifications

### /uber-driver-notification/add
To place notifications for a specific driver