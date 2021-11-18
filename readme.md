# user microservice

## Build the image

```bash
docker build -t user .
```

## Create network for all our microservices

```bash
docker network create rso
```

## Run database in network
```bash
docker run -d --name pg-user --network="rso" -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=users -p 5432:5432 postgres:13
```

## Run Consul config server
```bash
docker run -d --name consul -p 8500:8500 --network=rso consul:latest
```

## Run the container in network
```bash
docker run -d -p 8080:8080 --name user --network="rso" -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://pg-user:5432/users -e KUMULUZEE_CONFIG_CONSUL_AGENT=http://consul:8500 user
```

## Run the container from Docker hub in network

```bash
docker run -d -p 8080:8080 --name user --network="rso" anzeha/user:latest
```