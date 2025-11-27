FROM eclipse-temurin:17-jdk
COPY target/EmployeeApp.jar /usr/app
WORKDIR /usr/app
EXPOSE 9090
CMD ["java", "-jar", "EmployeeApp.jar"]
