# ms-reactive Project

## Generation

```bash
mvn com.redhat.quarkus.platform:quarkus-maven-plugin:2.13.5.Final-redhat-00002:create \
    -DprojectGroupId=com.home.example -DprojectArtifactId=ms-reactive
```

## Maven configuration

* Rest: quarkus-resteasy-reactive
* JSON: quarkus-resteasy-reactive-jackson
* Panache: quarkus-hibernate-reactive-panache
* MySQL: quarkus-reactive-mysql-client

## Run commands

* For running in development mode use: mvn quarkus:dev. It will use the dev profile and dev configurations
    * if we have any errors, we can re-run the application typing 's' in the console

## Testing

* If you run with 'mvn quarkus:dev' and then press 'r', quarkus will launch the tests. Those tests will be executed again when you modified and the application is already launched. 