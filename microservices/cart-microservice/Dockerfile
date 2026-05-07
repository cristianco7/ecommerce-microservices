FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

RUN apk add --no-cache maven

COPY . .

RUN mvn clean package -DskipTests -pl microservices/cart-microservice -am

#Etapa 2: Runtime (Imagen final optimizada)
FROM eclipse-temurin:17-jre-alpine
RUN apk add --no-cache curl bash
WORKDIR /app

COPY --from=build /app/microservices/cart-microservice/target/*.jar app.jar

#Exponer en el puerto del Config server
EXPOSE 8092

ENTRYPOINT ["java", "-jar", "app.jar"]