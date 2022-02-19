package com.mohammad.replicanode.config;

import com.mohammad.replicanode.utils.CacheUtils;
import com.mohammad.replicanode.cluster.Replica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Timer;
import java.util.TimerTask;

@Component
public class ChangeChecker extends TimerTask {

    public void cacheCleanerHelper() {
        Boolean isChanged =  Replica.create().isThereMasterChanges();
        if (isChanged)
            CacheUtils.clearCache();
    }

    @Override
    public void run() {
        cacheCleanerHelper();
    }

    @Autowired
    public void cacheCleaner(){
        Timer timer = new Timer();
        timer.schedule(new ChangeChecker(),15000,900);
    }
}
