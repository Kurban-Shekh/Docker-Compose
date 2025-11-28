# 1. Build Stage
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy Maven wrapper
COPY mvnw .
COPY .mvn .mvn

# Copy pom.xml and download dependencies first (better caching)
COPY pom.xml .
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Build the app
RUN ./mvnw clean package -DskipTests


# 2. Runtime Stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy only the built jar from step 1
COPY --from=build /app/target/*.jar app.jar

# Expose your app port
EXPOSE 9090

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
