package com.mohammad.replicanode.config;

import com.mohammad.replicanode.utils.CacheUtils;
import com.mohammad.replicanode.utils.ReplicaUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChangeChecker {

    @Bean
    public void cacheCleaner() {
        if (ReplicaUtils.isThereMasterChanges())
            CacheUtils.clearCache();
    }
    
}
