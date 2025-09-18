# Imagen Java
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# JAR desde el host al contenedor
COPY target/accounts-service-0.0.1-SNAPSHOT.jar app.jar

# Puerto expuesto
EXPOSE 8081

# Comando para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
