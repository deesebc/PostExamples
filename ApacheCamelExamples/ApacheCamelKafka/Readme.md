# ms-camel-quarkus-kafka

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
mvn compile quarkus:dev
```

## run kafka locally

```shell script
docker run -d --name=kafka -p 9092:9092 apache/kafka
```

## Generate Image

```sh
mvn package -Pnative -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.native -t deesebc/ms-camel-quarkus-kafka .
docker push deesebc/ms-camel-quarkus-kafka
```

## Launch kubernetes

```sh
kind create cluster --config kind-config.yaml --name kind-basic
kubectl apply -f ms-k8s-camel-quarkus-kafka.yaml
kubectl delete -f ms-k8s-camel-quarkus-kafka.yaml
kind delete cluster --name kind-basic
```

## test it

* Check if it's alive

```sh
curl --location 'http://localhost:30000/q/health/live'      
```

## Logs

```sh
kubectl logs deployment/ms-k8s-cml-qks-kafka --all-containers=true 
```

## Documentation

