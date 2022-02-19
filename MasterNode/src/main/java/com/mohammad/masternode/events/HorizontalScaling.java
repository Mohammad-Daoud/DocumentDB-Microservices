package com.mohammad.masternode.events;


import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.cluster.Replica;
import com.mohammad.masternode.utils.AppLogger;
import com.mohammad.masternode.utils.ThreadUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HorizontalScaling {

    private final AppLogger LOGGER = AppLogger.create("HorizontalScaling logger: ");
    private  int maxThreadNumber = 32;

    @Bean
    public void doScaling() {
     int currentThreadNumber = ThreadUtils.threadCounter();
        if (currentThreadNumber > maxThreadNumber) {
            maxThreadNumber += currentThreadNumber;
            Replica.create();
            LOGGER.log("Scaling Active 'added new replica");
            LOGGER.log("Number of Replicas: "+ MasterNode.getInstance().getReplicaGroup());
        }
    }
}
