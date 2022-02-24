package com.mohammad.masternode.api.services;

import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.cluster.Observer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MasterService {

    public List<Observer> getReplicaGroup(){
        return new ArrayList<>(MasterNode.getInstance().getReplicaGroup());
    }

}
