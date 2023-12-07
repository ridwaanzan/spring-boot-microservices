### README
First of all, this repository has many microservice inside it.
Better if you want to build it to Docker, you can run it using
docker-compose command.

### This project build with:
1. Spring Boot 3.x
   1. Spring Security
   2. JPA Hibernates
   3. JWT Token
   4. Spring Mail
   5. Lombok
2. MySQL
3. Kafka

### Microservices and Purpose:
1. membership : Use this as User and Authentication.
2. payment-dummy : Use this as dummy of core surrounding.
3. satu : Use this to recieve request payment.
4. dua : Use this to listening payment event.
5. tiga : Use this to handle request cardNumber and amount.
6. empat : Use this to handle request cardNumber and CVV.
7. lima : Use this to handle request cardNumber and cardHolderName.

### Kafka Topics:
You need to make Kafka topic name: <b>make_payment</b>