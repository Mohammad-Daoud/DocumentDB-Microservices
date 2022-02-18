package com.mohammad.masternode.api.services;

import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.cluster.Observer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MasterService {


    private static int replicaSize = MasterNode.getInstance().getReplicaGroupSize(); ;

    public static boolean isThereChanges() {
        if (replicaSize < MasterNode.getInstance().getReplicaGroupSize()) {
            replicaSize = MasterNode.getInstance().getReplicaGroupSize();
            return true;
        }
        return false;
    }

    public List<Observer> getReplicaGroup(){
        List<Observer> replicaGroup = new ArrayList<>(MasterNode.getInstance().getReplica());
        System.out.println(replicaGroup);
        return replicaGroup;
    }

}
