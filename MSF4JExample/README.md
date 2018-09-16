# MSF4JExample

## Info

Project to explain how it works MSF4J version: 2.6.2

## Execution

Follow the steps:
- Execute 'mvn package' over the application
- Execute 'java -Dmsf4j.conf=src/main/resources/metrics.yaml -jar target/MS4JExample-1.3.0-SNAPSHOT.jar'
- Launch a call to with basic auth: username/password
	- http://localhost:9090/book/list
	- http://localhost:9090/book/8
	- http://localhost:9090/swagger

## Tags

 WSO2, REST, JAX-WS
