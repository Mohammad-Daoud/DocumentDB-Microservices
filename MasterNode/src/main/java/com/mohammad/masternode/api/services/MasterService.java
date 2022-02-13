package com.mohammad.masternode.api.services;

import com.mohammad.masternode.cluster.MasterNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class MasterService {

    @Autowired
    MasterNode controller;

    private int replicaSize ;

    public boolean isThereChanges() {
        replicaSize = controller.getReplicaGroupSize();
        if (replicaSize < controller.getReplicaGroupSize()) {
            replicaSize = controller.getReplicaGroupSize();
            return true;
        }
        return false;
    }

}
