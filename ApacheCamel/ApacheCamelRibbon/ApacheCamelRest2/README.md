# ApacheCamelRestExample

## Info

Project to explain how it works Apache Camel 2.25.1. A basic CRUD Example, with Mock, SQL and JPA. 

## Links

You can see an explanation in Spanish here: 

* CRUD: https://DesarrolloJavaYYo.blogspot.com/2020/06/apache-camel-rest-crud-service-basico.html
* CRUD-SQL: https://DesarrolloJavaYYo.blogspot.com/2020/07/apache-camel-rest-crud-y-el-componente.html
* Portainer: https://DesarrolloJavaYYo.blogspot.com/2020/09/portainer-como-facilitarte-el-trabajo.html
* Jaeger: https://DesarrolloJavaYYo.blogspot.com/2020/10/apache-camel-control-de-trazas-con.html
* Caffeine: https://DesarrolloJavaYYo.blogspot.com/2020/11/apache-camel-uso-de-cache-con-caffeine.html

You can see an explanation in English here: 

* CRUD: https://medium.com/@danielblancocuadrado/apache-camel-crud-rest-initiation-393e293dc013
* CRUD-SQL: https://medium.com/@danielblancocuadrado/apache-camel-rest-crud-and-the-sql-component-ad7c1eb21c94
* Portainer: https://medium.com/@danielblancocuadrado/portainer-how-to-make-your-work-with-docker-easier-b3f8d7c0aeff
* Jaeger: https://danielblancocuadrado.medium.com/apache-camel-trace-control-with-jaeger-13edf87043bc
* Caffeine: https://danielblancocuadrado.medium.com/apache-camel-use-of-cache-with-caffeine-63a147aac785

## Jaeger

You need to add export JAEGER_SERVICE_NAME=Camel to works this example.

### Micrometer - Graphite

'''
docker run -d \
  --name graphite \
  --restart=always \
  -p 80:80 \
  -p 2003-2004:2003-2004 \
  -p 2023-2024:2023-2024 \
  -p 8125:8125/udp \
  -p 8126:8126 \
  graphiteapp/graphite-statsd
'''
  

## Tags

API, web services, integration, ESB, apache, camel, REST, CRUD