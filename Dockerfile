FROM openjdk:25-jdk
COPY target/EmployeeApp.jar /usr/app
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar","EmployeeApp.jar"]