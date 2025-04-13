# ---------- Build Stage ----------
    FROM maven:3.9.6-eclipse-temurin-17 AS build
    WORKDIR /app
    
    # Copy pom.xml and download dependencies first (for caching)
    COPY pom.xml .
    RUN mvn dependency:go-offline
    
    # Copy the rest of the source
    COPY . .
    
    # Build the application
    RUN mvn clean package -DskipTests
    
    # ---------- Runtime Stage ----------
    FROM eclipse-temurin:17-jdk-alpine
    WORKDIR /app
    
    # Copy the built JAR from the build stage
    COPY --from=build /app/target/*.jar app.jar
    
    # Expose port (change if your app uses a different port)
    EXPOSE 8080
    
    # Run the application
    ENTRYPOINT ["java", "-jar", "app.jar"]
    