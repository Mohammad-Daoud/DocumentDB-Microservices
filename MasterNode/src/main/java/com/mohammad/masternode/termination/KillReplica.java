package com.mohammad.masternode.termination;

import com.mohammad.masternode.cluster.MasterNode;
import com.mohammad.masternode.cluster.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;





@Component
public class KillReplica {

    @PreDestroy
    public void shutdownAllReplicas(){
        MasterNode.getInstance().getReplica().forEach(Observer::killReplica);
    }
}
