package com.mohammad.masternode.events;

import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.cluster.Observer;
import com.mohammad.masternode.utils.AppLogger;
import org.springframework.stereotype.Component;


import javax.annotation.PreDestroy;
import java.util.Set;

@Component
public class StopReplica {
    private final AppLogger LOGGER = AppLogger.create("StopReplica logger: ");

    @PreDestroy
    public void shutdownAllReplicas() {
        LOGGER.log("replicas have been stopped successfully !");
        Set<Observer> replicas = MasterNode.getInstance().getReplicaGroup();
        MasterNode.getInstance().clearAllReplicas(replicas);
    }
}
