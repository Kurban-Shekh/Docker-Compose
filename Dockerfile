FROM eclipse-temurin:17-jdk
WORKDIR /usr/app
COPY target/EmployeeApp.jar .
EXPOSE 9090
CMD ["java", "-jar", "EmployeeApp.jar"]
