package com.mohammad.masternode.cluster;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.mohammad.masternode.utils.ThreadUtils.threadCounter;

@Component
public class MasterNode implements Subject {
    private final List<Observer> REPLICA_GROUP = new ArrayList<>();

    public MasterNode() {
    }

    @Override
    public void addReplica(Observer replica) {
        REPLICA_GROUP.add(replica);
    }

    @Override
    public void deleteReplica(Observer replica) {
        REPLICA_GROUP.remove(replica);
    }

    @Override
    public void notifyAllReplicas() {
        if (threadCounter() > 20) {
            REPLICA_GROUP.forEach(replica -> {
                replica.killPort();
                deleteReplica(replica);
            });

            addReplica(Replicas.create());

        }
        REPLICA_GROUP.forEach(Observer::update);
    }

}
