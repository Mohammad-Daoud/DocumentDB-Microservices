package com.mohammad.masternode.events;

import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.cluster.Replica;
import com.mohammad.masternode.utils.AppLogger;
import com.mohammad.masternode.utils.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HorizontalScaling {

    private final AppLogger LOGGER = AppLogger.create("HorizontalScaling logger: ");

    @Autowired
    public void doScaling() {
        if (ThreadUtils.threadCounter() > 20) {
            MasterNode.getInstance().addReplica(Replica.create());
            LOGGER.log("Scaling Active 'added new replica");
        }
    }

}
