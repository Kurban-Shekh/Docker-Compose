## 1. Launch EC2 Instance (AWS Console)
Choose:

Instance Type: t3.micro (Free Tier eligible)

AMI: Amazon Linux 2023

Storage: 8 GB

## 2. Connect to EC2
`ssh -i your-key.pem ec2-user@YOUR_PUBLIC_IP`

## 3. Update System
`sudo dnf update -y`

## 4. Install Java 17 (Amazon Corretto)
`sudo dnf install -y java-17-amazon-corretto` <br>
`sudo dnf install -y java-17-amazon-corretto-devel` <br>
`java -version`

## 5. Install Maven
`sudo dnf install -y maven`

## 6. Install git
`sudo dnf install -y git`

## 7. Install Docker
`sudo dnf install -y docker` <br>
`sudo systemctl start docker` <br>
`sudo systemctl enable docker`<br><br>
Add EC2 user to Docker group:<br>
`sudo usermod -aG docker $USER`<br><br>
<strong> Logout + login again </strong><br>
`docker --version`<br>
`docker ps`

## 8. Install Docker Compose
`sudo curl -SL https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) \
  -o /usr/local/bin/docker-compose` <br>

`sudo chmod +x /usr/local/bin/docker-compose`

<strong> check version </strong><br>
`docker-compose --version`

## 9. Clone your project from github
`git clone https://github.com/your/repo.git`<br>
`cd your-project`

## 10. Create the jar file
`mvn clean package -DskipTests`

## 11. Create Dockerfile

Create a file named **Dockerfile** in your project root and add the following content:

```dockerfile
FROM eclipse-temurin:17-jdk

WORKDIR /usr/app

COPY target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
```

## 12. docker-compose.yml example
```
services:
  mysql:
    image: mysql:8.0
    container_name: mysql_db
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: docker_db
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql
    networks:
      - springboot-mysql

  springboot:
    build: .
    container_name: springboot_app
    restart: always
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/docker_db
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root
    depends_on:
      - mysql
    networks:
      - springboot-mysql

volumes:
  mysql_data:

networks:
  springboot-mysql:
```

## 13. Build & Run Docker Compose

Start containers:<br>
`docker-compose up -d`<br><br>

Check running containers:<br>
`docker ps`<br><br>

View logs for the Spring Boot container:<br>
`docker logs -f springboot_app`

## 14. Test application inside EC2
curl http://localhost:8080

## 15. Test in Browser

Open:

http://YOUR_EC2_PUBLIC_IP:8080

## 16. Push image to Docker Hub (optional)

Login:

docker login -u your-dockerhub-username


Build image:

docker build -t employee-app .


Tag:

docker tag employee-app:latest your-username/employee-app:latest


Push:

docker push your-username/employee-app:latest

## 17. Stop and Remove Containers
docker-compose down

