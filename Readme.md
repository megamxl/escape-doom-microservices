To build all containers remote
``` bash
  mvn compile jib:build -pl services/gateway-service,services/data-service
```

To build all local containers
``` bash
  mvn compile jib:dockerBuild -pl services/gateway-service,services/data-service
```