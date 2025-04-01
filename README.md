Для создания бд:
1. psql
2. CREATE DATABASE lib

Для запуска проекта:
1. git clone https://github.com/Emelyanov535/library-service.git
2. cd library-service
3. mvn clean package
4. cd target
5. java -jar library-service-0.0.1-SNAPSHOT.jar --DB_HOST=localhost --DB_PORT=5432 --DB_NAME=lib --DB_USERNAME=postgres --DB_PASSWORD=postgres
   (Данные указываются от вашего пользователя и то, где у вас развернута база)


Swagger доступен по ссылке: http://localhost:8080/swagger-ui/index.html