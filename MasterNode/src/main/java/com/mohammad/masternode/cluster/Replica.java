package com.mohammad.masternode.cluster;

import com.mohammad.masternode.utils.ShellExecutor;


public class Replica implements Observer {
    private PortGenerator GENERATOR = PortGenerator.getInstance();
    private final String REPLICA_FILE = "C:/Users/mdss4/Documents/Atypon/DocumentDB/ReplicaNode/target/replica-node-0.0.1-SNAPSHOT.jar";
    private final int PORT;


    private Replica() {
        String runReplicaCommand = "java -jar " + REPLICA_FILE + " --server.port=" + GENERATOR.generateNewPort();
        this.PORT = GENERATOR.getPort();

        ShellExecutor.create(runReplicaCommand).start();
    }

    public static Replica create() {
        return new Replica();
    }

    @Override
    public void update() {
        MasterNode.getInstance().addReplica(Replica.create());
    }

    @Override
    public void killReplica() {
        String killCommand = "npx kill-port " + PORT;
        ShellExecutor.create(killCommand).start();
    }


    public int getPORT() {
        return PORT;
    }

    @Override
    public String toString() {
        return "{ port:" + PORT + "}";
    }
}
