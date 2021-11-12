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
docker run -d --name user_db --network="rso" -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=users -p 5432:5432 postgres:13
```

## Run the container in network

```bash
docker run -p 8080:8080 --name user --network="rso" -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://user_db:5432/users user
```

## Run the container from Docker hub in network

```bash
docker run -p 8080:8080 --name user --network="rso" anzeha/user:latest
```