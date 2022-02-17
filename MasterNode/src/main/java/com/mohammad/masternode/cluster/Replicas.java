package com.mohammad.masternode.cluster;

import com.mohammad.masternode.utils.ShellExecutor;


public class Replicas implements Observer {
    private int port = 8999;
    private final String REPLICA_FILE = "C:/Users/mdss4/Documents/Atypon/DocumentDB/ReplicaNode/target/replica-node-0.0.1-SNAPSHOT.jar";


    private Replicas() {
        String runReplicaCommand = "java -jar " + REPLICA_FILE + " --server.port=" + portGenerator();
        ShellExecutor.create(runReplicaCommand).start();
    }

    public static Replicas create() {
        return new Replicas();
    }

    @Override
    public void update() {
        String runReplicaCommand = "java -jar " + REPLICA_FILE + " --server.port=" + portGenerator();
        System.out.println("replica generated with port "+port);
        ShellExecutor.create(runReplicaCommand).start();
    }

    @Override
    public void killReplica() {
        String killCommand = "npx kill-port " + port ;

        ShellExecutor.create(killCommand).start();
    }

    private int portGenerator() {
        return ++port;
    }


    @Override
    public String toString() {
        return "{ port:" + port + "}";
    }
}
