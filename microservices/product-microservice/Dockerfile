#Etapa 1: Buikd
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

RUN apk add --no-cache maven

COPY . .

RUN mvn clean package -DskipTests -pl microservices/product-microservice -am

#Etapa 2: Runtime (Imagen final optimizada)
FROM eclipse-temurin:17-jre-alpine
RUN apk add --no-cache curl bash
WORKDIR /app

COPY --from=build /app/microservices/product-microservice/target/*.jar app.jar

EXPOSE 8092

ENTRYPOINT ["java", "-jar", "app.jar"]