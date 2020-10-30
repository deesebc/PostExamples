# ApacheCamelRestExample

## Info

Project to explain how it works Apache Camel + Quarkus. A basic CRUD Example with SQL

## Deploy

https://camel.apache.org/camel-quarkus/latest/user-guide/first-steps.html

mvn clean compile quarkus:dev

## Package

https://camel.apache.org/camel-quarkus/latest/user-guide/first-steps.html

1. 

mvn clean package
java -jar target/*-runner.jar

2. 

mvn clean package -Pnative
./target/*-runner

## Generate Runner for Image

** Change application.properties : Put sandbox-mysql instead of localhost **

mvn clean package -Pnative -Dquarkus.native.container-build=true \
 -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-native-image:20.2.0-java8 \
 -Dquarkus.native.container-runtime=docker
 
## Generate Image 

docker build -t camel-quarkus-runner .

## Link

You can see an explanation in Spanish here: 

* CRUD: 

You can see an explanation in English here: 

* CRUD: 
## Tags

API, web services, integration, ESB, apache, camel, REST, CRUD, Quarkus