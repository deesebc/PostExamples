# FlywayExample

Project to explain how it works Flyway.

To run the example it must be necessary have docker installed and execute the following command:

docker run -d --name flyway-example-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql:5.6

Later you can execute this command inside the root folder of the project:

./gradlew flywayMigrate -i

You can see an explanation in Spanish here: http://jakartaeeyyo.blogspot.com/2018/xxxxxx