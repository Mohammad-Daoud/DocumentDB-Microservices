package com.mohammad.replicanode.config;

import com.mohammad.replicanode.utils.CacheUtils;
import com.mohammad.replicanode.cluster.Replica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class ChangeChecker extends TimerTask {

    public void cacheCleanerHelper() {
       Replica replicaInstance = Replica.create();
        if (replicaInstance.isThereMasterChanges())
            CacheUtils.clearCache();
    }

    @Override
    public void run() {
        System.out.println("Change checker has been called");
        cacheCleanerHelper();
    }

    @Autowired
    public void cacheCleaner(){
        Timer timer = new Timer();
        timer.schedule(new ChangeChecker(),30000,100000);// the check for changes will be every 13 second
    }
}
