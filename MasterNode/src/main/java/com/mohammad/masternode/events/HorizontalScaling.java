package com.mohammad.masternode.events;


import com.mohammad.masternode.cluster.Replica;
import com.mohammad.masternode.utils.AppLogger;
import com.mohammad.masternode.utils.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class HorizontalScaling {

    private final AppLogger LOGGER = AppLogger.create("HorizontalScaling logger: ");
    private final int CURRENT_THREADS_NUMBER = ThreadUtils.threadCounter();
    private final int MAX_THREADS_NUMBER = 32;

    public void doScaling() {
        if (CURRENT_THREADS_NUMBER > MAX_THREADS_NUMBER ) {
            Replica.create();
            LOGGER.log("Scaling Active 'added new replica");
        }
    }

}
