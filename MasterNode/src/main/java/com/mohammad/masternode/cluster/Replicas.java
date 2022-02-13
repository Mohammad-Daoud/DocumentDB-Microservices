package com.mohammad.masternode.cluster;

import org.springframework.stereotype.Component;

import static com.mohammad.masternode.utils.ShellExecutor.runShellCommand;



@Component
public class Replicas implements Observer {
    private final String REPLICA_FILE = "C:/Users/mdss4/Documents/Atypon/DocumentDB/ReplicaNode/target/replica-node-0.0.1-SNAPSHOT.jar";
    private final String RUN_REPLICA_COMMAND = "java -jar "+REPLICA_FILE+" --server.port="+portGenerator();
    private int port = 8999;

    private Replicas(){
        runShellCommand(RUN_REPLICA_COMMAND);
    }
    public static Replicas create(){
        return new Replicas();
    }

    @Override
    public void killPort(){
        runShellCommand("npx kill-port "+getPort());
    }

    @Override
    public void update(){
        runShellCommand(RUN_REPLICA_COMMAND);
    }

    private int portGenerator(){
        return ++port;
    }

    public int getPort() {
        return port;
    }
}
