package com.mohammad.masternode.cluster;

import com.mohammad.masternode.utils.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HorizontalScaling {

    @Bean
    public void startReplica(){
        MasterNode.getInstance().addReplica(Replicas.create());
    }
    @Autowired
    public void doScaling(){
        if (ThreadUtils.threadCounter() > 8) {
            MasterNode.getInstance().addReplica(Replicas.create());
        }
    }

}
