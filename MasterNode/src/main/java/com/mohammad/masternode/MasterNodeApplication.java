package com.mohammad.masternode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.mohammad.masternode.cluster.Replica.setReplicaFileLocation;


@SpringBootApplication
public class MasterNodeApplication {

	public static void main(String[] args)  {
		replicaNodeLocationSetup();
		SpringApplication.run(MasterNodeApplication.class, args);
	}

	public static void replicaNodeLocationSetup(){
		setReplicaFileLocation("C:/Users/mdss4/Documents/Atypon/DocumentDB/ReplicaNode");
	}

}
