package com.mohammad.replicanode;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.mohammad.replicanode.io.FileManager.setMaster;


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
