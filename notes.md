
## Deploying our services and client app to docker

- eureka-server
- api-gateway
- authentication
- customer-service
- product-service


```sh
cd eureka-server
./mvnw clean
./mvnw package
docker build -t app .
cd ..
cd customer-service
./mvnw clean
./mvnw package
docker build -t customer-service .
cd ..
cd api-gateway
./mvnw clean
./mvnw package
docker build -t api-gateway .
cd product-service
./mvnw clean
./mvnw package
docker build -t product-service .
docker images

```

