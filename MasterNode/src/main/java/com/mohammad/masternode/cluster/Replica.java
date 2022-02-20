package com.mohammad.masternode.cluster;

import com.mohammad.masternode.utils.AppLogger;
import com.mohammad.masternode.utils.ShellExecutor;
import org.springframework.web.client.RestTemplate;


public class Replica implements Observer {
    private static final AppLogger LOGGER = AppLogger.create("Replica logger: ");
    private final PortGenerator GENERATOR = PortGenerator.getInstance();
    private final String REPLICA_FILE = "C:/Users/mdss4/Documents/Atypon/DocumentDB/ReplicaNode/target/replica-node-0.0.1-SNAPSHOT.jar";
    private final String REPLICA_RUN_COMMAND = "java -jar " + REPLICA_FILE + " --server.port=" + GENERATOR.generateNewPort();
    private final ReplicaState STATE = ReplicaState.getInstance();
    private final int PORT;


    private Replica() {
        this.PORT = GENERATOR.getPort();
        ShellExecutor executor = ShellExecutor.create(REPLICA_RUN_COMMAND);
        executor.start();

        MasterNode.getInstance().addReplica(this);
        LOGGER.log("Replica created with port " + PORT);
    }

    public static Replica create() {
        return new Replica();
    }

    @Override
    public void update() {
        String message = updateHelper(PORT);
        LOGGER.log(message);
    }

    @Override
    public void killReplica() {
        String killCommand = "npx kill-port " + PORT;
        ShellExecutor.create(killCommand).start();
        LOGGER.log("killed replica " + PORT);
    }

    private String updateHelper(int port){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:"+port+"/read/clear-cache/true";
        return restTemplate.getForObject(url, String.class);
    }

    public int getPORT() {
        return PORT;
    }

    @Override
    public String toString() {
        return "{ port:" + PORT + "}";
    }
}
