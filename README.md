

# DocumentDB
## Description
A microservices REST API using SpringBoot framework to build
  NoSql/DocumentDB in JSON format, with *BasicAuth* authentication.
  Also, a demo *Todo* web application.

## Installation
You should have ***Maven*** and ***JDK 11.***
  and run ***mvn clean install*** on each project **Very important**.

Then, go to ReplicaNode app and write the directory that where the application is.
```
@SpringBootApplication
public class ReplicaNodeApplication {

	public static void main(String[] args) {
		masterDirectorySetup();
		SpringApplication.run(ReplicaNodeApplication.class, args);
	}
	
	public static void masterDirectorySetup (){
		setMaster("YOUR FILE LOCATION");
	}
}
```
    
Then, run each application: except ReplicaNode
  - *1. NamingServer* with port 8761
  - *2. ApiGateway* with port 8080
  - *3. MasterNode* with port 8000
  - *4. ReplicaNode* with ports [9000- unknown] 
    will be run automatically by MasterNode

  - *5. Todo app* with port 8090

## Configuration
### Username and Password
The admin username ***“root”*** with password ***“root”***
  I recommend changing the root password asap.
* if you change the username and password for DB don't forget 
to change it in demo App in ```ResponseHandler``` class
```
 private final String USERNAME = "root";
 private final String PASSWORD = "root" ; 
```
#### API Calls
To know what operation we can do just go to:

##### For controller node (administrative tasks)
<http://localhost:8000/master/index.html>

#### For replicas node (reading tasks)
<http://localhost:9000/read/index.html>

#### Demo
<http://localhost:8090/login>

## Author
[Mohammad Daoud](https://www.linkedin.com/in/mohammad-daoudx/)

## Acknowledgment
My thanks and appreciation for Atypon co. Especially [Motasem Aldiab](https://www.linkedin.com/in/maldiab) and
  [Fahed Jubair](https://www.linkedin.com/in/fahed-jubair-52b84882/)
  For support and teaching me all knowledge that helped me on my journey. May Allah
  bless you.