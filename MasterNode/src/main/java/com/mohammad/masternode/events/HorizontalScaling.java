package com.mohammad.masternode.events;

import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.cluster.Replica;
import com.mohammad.masternode.utils.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HorizontalScaling {

    @Autowired
    public void doScaling(){
        if (ThreadUtils.threadCounter() > 20) {
            MasterNode.getInstance().addReplica(Replica.create());
        }
    }

}
