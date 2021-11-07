# Hazelcast Example

Example to see how it works Hazelcast

## Docker Compose

Run docker compose up in src/test/resources folder

## Testing

Execute GET http://localhost:8080/RestEasyService/rest/info/property to see the value of the property 'property'.

Execute POST http://localhost:8080/RestEasyService/rest/info/property/value to set the value for the property and obtain the previous value

To call tomcat-server1 use localhost:8080 and for tomcat-server2 use localhost:8082