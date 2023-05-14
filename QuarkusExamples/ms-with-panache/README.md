# ms-with-panache Project

## Generation

```bash
mvn com.redhat.quarkus.platform:quarkus-maven-plugin:2.13.5.Final-redhat-00002:create \
    -DprojectGroupId=com.home.example -DprojectArtifactId=ms-with-panache
```

## Maven configuration

* Rest: quarkus-resteasy-reactive
* Panache: quarkus-hibernate-orm-panache
* JSON: quarkus-resteasy-reactive-jackson

## Run commands

* For running in development mode use: mvn quarkus:dev. It will use the dev profile and dev configurations
    * if we have any errors, we can re-run the application typing 's' in the console