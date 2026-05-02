FROM maven:3.9-eclipse-temurin-21

# Set working directory
WORKDIR /workspace

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy entire project
COPY . .

# Install project
RUN mvn clean install -DskipTests

# Set environment variables
ENV BROWSER=chrome
ENV HEADLESS=false

# Default command to run tests
CMD ["mvn", "clean", "test"]
