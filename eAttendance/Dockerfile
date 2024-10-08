# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY ./eAttendance/target/e_attendance.jar /app/e_attendance.jar

# Copy the entire project directory into the container
COPY ./eAttendance /app

# Expose the port the app runs on
EXPOSE 8080

# Set environment variables for database configuration
ENV SPRING_DATASOURCE_URL=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}
ENV SPRING_DATASOURCE_USERNAME=${DB_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD}

# Specify the command to run on container start
CMD ["java", "-jar", "e_attendance.jar"]
