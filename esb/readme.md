# ESB

to compile

`mvn clean compile package`

to run execute

`java -jar target/esb-0.0.1-SNAPSHOT.jar`

got to `htt://localhost:8983/api/services` to list all the registered services


This module works as an ESB. it provides 3 REST methods that allow to 
 * list all registered services
 * register a service
 * unregister a service
 
 and the main one that allows to reach all the registered services.
