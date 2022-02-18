package com.mohammad.masternode.events;

import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.cluster.Replica;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class StartReplica {

    @PostConstruct
    public void startReplica(){
        MasterNode.getInstance().addReplica(Replica.create());
    }

}
