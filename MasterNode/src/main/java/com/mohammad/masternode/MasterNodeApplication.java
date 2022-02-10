package com.mohammad.masternode;

import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.cluster.Replicas;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MasterNodeApplication {

	public static void main(String[] args)  {

		SpringApplication.run(MasterNodeApplication.class, args);
	}
	/*@Bean
	public void startReadReplica(){
		new MasterNode().addReplica(Replicas.create());
	}*/
}
