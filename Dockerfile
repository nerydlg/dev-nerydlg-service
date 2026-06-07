# syntax=docker/dockerfile:1

# --- Build stage ---
FROM eclipse-temurin:22-jdk AS build
WORKDIR /app

# Cache dependencies first: copy only what's needed to resolve them
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw -B dependency:go-offline

# Now build the application
COPY src/ src/
RUN ./mvnw -B clean package -DskipTests

# --- Runtime stage ---
FROM eclipse-temurin:22-jre
WORKDIR /app

# Run as a non-root user
RUN groupadd --system spring && useradd --system --gid spring spring

COPY --from=build /app/target/*.jar app.jar

# Writable logs dir for the configured log file
RUN mkdir -p /app/logs && chown -R spring:spring /app
USER spring

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
