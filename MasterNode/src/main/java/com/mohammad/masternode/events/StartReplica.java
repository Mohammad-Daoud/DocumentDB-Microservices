package com.mohammad.masternode.events;

import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.cluster.Replica;
import com.mohammad.masternode.utils.AppLogger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class StartReplica {
    private final AppLogger LOGGER = AppLogger.create("StartReplica logger: ");
    @PostConstruct
    public void startReplica(){
        Replica.create();
        LOGGER.log("replica started !");
    }

}
