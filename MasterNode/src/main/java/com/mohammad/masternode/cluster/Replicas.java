package com.mohammad.masternode.cluster;

import com.mohammad.masternode.utils.ShellExecutor;
import org.springframework.stereotype.Component;



@Component
public class Replicas implements Observer {
    private int port = 8999;
    private final String REPLICA_FILE = "C:/Users/mdss4/Documents/Atypon/DocumentDB/ReplicaNode/target/replica-node-0.0.1-SNAPSHOT.jar";
    private  ShellExecutor executor ;

    private Replicas() {
        String runReplicaCommand = "java -jar " + REPLICA_FILE + " --server.port=" + portGenerator();
        this.executor = ShellExecutor.create(runReplicaCommand);
        this.executor.start();
    }

    public static Replicas create() {
        return new Replicas();
    }

    @Override
    public void update() {
        executor.start();
    }

    @Override
    public void killReplica() {
        String killCommand = "for /f \"tokens=5\" %a in ('netstat -aon ^| find \":\""+getPort()+" ^| find \"LISTENING\"') do taskkill /f /pid %a\n";
        executor = ShellExecutor.create(killCommand);
        executor.start();
    }

    private int portGenerator() {
        return ++port;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "{ port:" + port+"}" ;
    }
}
