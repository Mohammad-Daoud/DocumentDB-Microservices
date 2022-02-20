package com.mohammad.masternode.events;



import com.mohammad.masternode.cluster.Replica;
import com.mohammad.masternode.utils.AppLogger;
import com.mohammad.masternode.utils.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

//@Component
public class HorizontalScaling extends TimerTask {

    private final AppLogger LOGGER = AppLogger.create("HorizontalScaling logger: ");
    private  int maxThreadNumber = 32;

    public void doScalingHelper() {
     int currentThreadNumber = ThreadUtils.threadCounter();
        if (currentThreadNumber > maxThreadNumber) {
            maxThreadNumber += currentThreadNumber;
            Replica.create();
            LOGGER.log("Scaling Active 'added new replica");
        }
    }

    @Override
    public void run(){
        doScalingHelper();
    }

    @Autowired
    public void doScaling(){
        Timer timer = new Timer();
        timer.schedule(new HorizontalScaling(),60000,60000);
    }
}
