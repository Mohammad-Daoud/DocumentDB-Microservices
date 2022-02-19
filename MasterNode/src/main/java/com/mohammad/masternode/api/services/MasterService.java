package com.mohammad.masternode.api.services;

import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.cluster.Observer;
import com.mohammad.masternode.cluster.ReplicaState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MasterService {

    private static final ReplicaState STATE = ReplicaState.getInstance();
    private static int replicaSize = STATE.getState();

    public static Boolean isThereChanges() {
        if (replicaSize < STATE.getState()) {
            replicaSize = STATE.getState();
            return true;
        }
        return false;
    }

    public List<Observer> getReplicaGroup(){
        return new ArrayList<>(MasterNode.getInstance().getReplicaGroup());
    }

}
