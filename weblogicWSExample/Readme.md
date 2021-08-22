# WeblogicWS Example

## Docker

- It's necessary to have an account in Oracle.com Network
- Access to Oracle Container Registry, login with your account and accept the User License. For example in this [page](https://container-registry.oracle.com/ords/f?p=113:4:105126891322231:::::).
- Modify the dockerproperties/domain.properties with the values of your user/password. 
- Run this command:
    - Use your Oracle account to login in container-registry.oracle.com to be able of downloading the image
    - Where *dockerproperties* is the full path to the folder that contain the domain.properties file. 

```bash
docker login container-registry.oracle.com

docker run -p 7001:7001 -p 9002:9002  -v {dockerpropertiesFolder}:/u01/oracle/properties container-registry.oracle.com/middleware/weblogic:14.1.1.0-dev-8
```

- Access to: [https://localhost:9002/console](Access to: https://localhost:9002/console)