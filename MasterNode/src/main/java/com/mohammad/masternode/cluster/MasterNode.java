package com.mohammad.masternode.cluster;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class MasterNode implements Subject {
    private final static Set<Observer> REPLICA_GROUP = Collections
            .newSetFromMap(new ConcurrentHashMap<>());
    private static volatile MasterNode controller;


    private MasterNode() {
    }

    public static MasterNode getInstance() {
        if (controller == null) {
            synchronized (MasterNode.class) {
                if (controller == null)
                    controller = new MasterNode();
            }
        }
        return controller;
    }

    @Override
    public void addReplica(Observer replica) {
        if (replica == null) return;
        REPLICA_GROUP.add(replica);
    }

    @Override
    public void deleteReplica(Observer replica) {
        if (replica == null) return;
        replica.killReplica();
        REPLICA_GROUP.remove(replica);
    }


    @Override
    public void notifyAllReplicas() {
        REPLICA_GROUP.forEach(Observer::update);;
    }


    public void clearAllReplicas(Set<Observer> observerSet) {
        observerSet.forEach(Observer::killReplica);
        observerSet.clear();
    }


    public Set<Observer> getReplicaGroup() {
        return REPLICA_GROUP;
    }

}
