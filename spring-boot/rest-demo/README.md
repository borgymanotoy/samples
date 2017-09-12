# spring-boot-samples > rest-demo

Spring Boot : RESTful Demo App

Created a small application that demonstrates RESTful API via Spring @RestController annotation.
All data for this sample is stored in a Set<User> inside the Class Users.
Demonstrates accessing the set data somelike of a CRUD operation of databases.

* To Run:
mvn spring-boot:run

OR in Linux Bash:
bash linuxRunSpringBoot.sh

Access the link:
http://localhost:8080/pages/index.html


* To try CORS and run on port 9000:

Go to the directory:
cd src/main/resources/static

Run the command:
php -S localhost:9000

OR in Linux Bash:
bash linuxRunPhpLocalServer.sh