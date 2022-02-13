package com.mohammad.masternode.cluster;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.mohammad.masternode.utils.ThreadUtils.threadCounter;

@Component
public class MasterNode implements Subject {
    private final static List<Observer> REPLICA_GROUP = new ArrayList<>();

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
        REPLICA_GROUP.forEach(Observer::update);
    }

    public int getReplicaGroupSize(){
        return getReplicaGroup().size();
    }
    public static List<Observer> getReplicaGroup() {
        return REPLICA_GROUP;
    }
}
