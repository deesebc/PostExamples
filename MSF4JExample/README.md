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
	
### OAuth Authentication

You need a IS deployed and running. 

To obtain a valid access token: 

*curl -v -k -X POST --basic -u <OAuth Client Key>:<OAuth Client Secret> -H "Content-Type: application/x-www-form-urlencoded;charset=UTF-8" -d "grant_type=client_credentials" https://localhost:9443/oauth2/token*

To call a service:

*curl -v -H "Authorization: Bearer <OAuth Access token>" http://localhost:9090/book/list*

### Websocket

You need to connect to: ws://localhost:9090/groupChat/{groupName}/{userName}

You can connect with different clients and send message between them. 

## Tags

 WSO2, REST, JAX-WS
