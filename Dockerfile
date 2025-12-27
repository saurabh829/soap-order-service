# =========================
# STAGE 1: BUILD
# =========================
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Set working directory
WORKDIR /build

# Copy pom.xml first (better Docker caching)
COPY pom.xml .

# Download dependencies
RUN mvn -B dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests


# =========================
# STAGE 2: RUNTIME
# =========================
FROM eclipse-temurin:21-jre

# Create non-root user (security best practice)
RUN useradd -r -u 1001 appuser

# Set working directory
WORKDIR /app

# Copy only the JAR from builder stage
COPY --from=builder /build/target/soap-order-service-1.0.0.jar app.jar

# Change ownership
RUN chown -R appuser:appuser /app

# Switch to non-root user
USER appuser

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
