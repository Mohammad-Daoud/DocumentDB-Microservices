package com.mohammad.masternode.events;

import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.cluster.Observer;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Set;


@Component
public class StopReplica {

    @PreDestroy
    public void shutdownAllReplicas() {
        Set<Observer> replicas = MasterNode.getInstance().getReplica();
        MasterNode.getInstance().clearMasterNode(replicas);
    }
}
