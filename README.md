

##DocumentDB

###Description

- A microservices REST API using SpringBoot framework to build

  NoSql/DocumentDB in JSON format, with *BasicAuth* authentication.

  Also, a demo *Todo* web application.

###Installation
  - You should have ***Maven*** and ***JDK 11.***
  and run *mvn clean install* on each project 
  then, go to replica and write the directory that where the application is .
  Then, run each application:

    - *1. NamingServer* with port 8761

    - *2. ApiGateway* with port 8080

    - *3. MasterNode* with port 8000

    - *4. ReplicaNode* with ports [9000- unknown]

    - *5. Todo app* with port 8090

##Configuration

###Username and Password


- The admin username ***“root”*** with password ***“root”***

  I recommend changing the root password asap.

###API Calls

- To know what operation we can do just go to:

####For controller node (administrative tasks)

- <http://localhost:8000/master/index.html>

####For replicas node (reading tasks)

- <http://localhost:9000/read/index.html>

####Demo
- <http://localhost:8090/login>

##Author

[Mohammad Daoud](https://www.linkedin.com/in/mohammad-daoudx/)

##Acknowledgment

- My thanks and appreciation for Atypon co. Especially [Motasem Aldiab](https://www.linkedin.com/in/maldiab and 
  [Fahed Jubair](https://www.linkedin.com/in/fahed-jubair-52b84882/)

For support and teaching me all knowledge that helped me on my journey. May Allah

bless you.

