package com.mohammad.replicanode.api.services;



import com.mohammad.replicanode.utils.CacheUtils;
import org.springframework.stereotype.Service;

@Service
public class ReplicaService {

    public void cleanCacheForChange(Boolean isChanged) {
        if (isChanged)
            CacheUtils.clearCache();
    }

}
