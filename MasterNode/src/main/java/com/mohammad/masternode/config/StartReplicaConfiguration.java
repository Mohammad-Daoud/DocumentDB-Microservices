package com.mohammad.masternode.config;

import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.cluster.Replicas;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartReplicaConfiguration {
    @Bean
    public void startReadReplica(){
        new MasterNode().addReplica(Replicas.create());
    }
}
