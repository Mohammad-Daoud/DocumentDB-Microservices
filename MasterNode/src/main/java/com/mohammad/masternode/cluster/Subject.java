package com.mohammad.masternode.cluster;

public interface Subject {
    void addReplica(Observer replica);
    void deleteReplica(Observer reb);
    void notifyAllReplicas();
}
