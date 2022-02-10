package com.mohammad.masternode.cluster;

import org.springframework.stereotype.Component;

import static com.mohammad.masternode.utils.ShellExecutor.runShellCommand;
import static com.mohammad.masternode.utils.ShellExecutor.writeReplicaBash;


@Component
public class Replicas implements Observer {

    private int port = 8999;

    private Replicas(){
        writeReplicaBash(portGenerator());
        runShellCommand("./replicaShell.sh");
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
        writeReplicaBash(portGenerator());
        runShellCommand("./replicaShell.sh'");
    }

    private int portGenerator(){
        return ++port;
    }

    public int getPort() {
        return port;
    }
}
