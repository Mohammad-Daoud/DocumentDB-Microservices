package com.mohammad.masternode.api.services;

import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.cluster.Observer;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MasterService {


    private int replicaSize  ;

    public boolean isThereChanges() {
        replicaSize = MasterNode.getInstance().getReplicaGroupSize();
        if (replicaSize < MasterNode.getInstance().getReplicaGroupSize()) {
            replicaSize = MasterNode.getInstance().getReplicaGroupSize();
            return true;
        }
        return false;
    }

    public Set<Observer> getReplicaGroup(){
        return MasterNode.getInstance().getReplica();
    }

}
