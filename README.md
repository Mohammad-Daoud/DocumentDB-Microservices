# DocumentDB

## Description

A microservices REST API using SpringBoot framework to build NoSql/DocumentDB in JSON format, with *BasicAuth*
authentication. Also, a demo *Todo* web application.

## Installation

You should have ***Maven***, ***JDK 11*** and ***[npm]<https://www.npmjs.com/package/kill-port>***.

go to ReplicaNode app and write the directory that where the MasterNode is:

```
@SpringBootApplication
public class ReplicaNodeApplication {

	public static void main(String[] args) {
		masterDirectorySetup();
		SpringApplication.run(ReplicaNodeApplication.class, args);
	}
	
	public static void masterDirectorySetup (){
		setMaster("C:/MasterNode");
	}
}
```
then, go to MasterNode and add the replicaNode project location:
```
@SpringBootApplication
public class MasterNodeApplication {

	public static void main(String[] args)  {
		replicaNodeLocationSetup();
		SpringApplication.run(MasterNodeApplication.class, args);
	}

	public static void replicaNodeLocationSetup(){
		setReplicaFileLocation("C:/ReplicaNode");
	}
}
```
final step run  on **ReplicaNode** application the following maven command:
- **very important to generate the **JAR** file**
``` 
mvn clean  
```

```
mvn install -Dmaven.test.skip=true 
```


Then, run each application: except ReplicaNode

- *1. NamingServer* with port 8761
- *2. ApiGateway* with port 8080
- *3. MasterNode* with port 8000
- *4. ReplicaNode* with ports [9000- unknown]
  *will be run automatically by MasterNode*

- *5. Todo app* with port 8090

## Configuration

### Username and Password

The admin username ***“root”*** with password ***“root”***
I recommend changing the root password asap.

* if you change the username and password for 
* DB don't forget to change it in demo App in ```ResponseHandler``` class

```
 private final String USERNAME = "root";
 private final String PASSWORD = "root" ; 
```

#### API Calls

To know what operation we can do just go to:

##### To know the operation for controller node (administrative tasks)

<http://localhost:8000/master/index.html>

#### To know the operation for replicas node (reading tasks)

<http://localhost:9000/read/index.html>

#### Demo

<http://localhost:8090/login>

## Author
- All the operation will be on API GATEWAY on port 8080:
    - <http://localhost:8080/read/**>
    - <http://localhost:8080/master/**>
[Mohammad Daoud](https://www.linkedin.com/in/mohammad-daoudx/)

## Acknowledgment

My thanks and appreciation for Atypon co. Especially [Motasem Aldiab](https://www.linkedin.com/in/maldiab) and
[Fahed Jubair](https://www.linkedin.com/in/fahed-jubair-52b84882/)
For support and teaching me all
knowledge that helped me on my journey. May Allah bless you.

## NOTE
you may have some problem when you try to re-run the application again
so, be sure to kill the jar file from ```task manager```