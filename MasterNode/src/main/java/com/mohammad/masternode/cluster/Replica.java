package com.mohammad.masternode.cluster;

import com.mohammad.masternode.utils.AppLogger;
import com.mohammad.masternode.utils.ShellExecutor;
import org.springframework.web.client.RestTemplate;


public class Replica implements Observer {
    private static final AppLogger LOGGER = AppLogger.create("Replica logger: ");
    private final PortGenerator GENERATOR = PortGenerator.getInstance();
    private final String REPLICA_RUN_COMMAND = "java -jar "
            + getReplicaFile()
            + " --server.port=" + GENERATOR.generateNewPort()
            + " --eureka.client.serviceUrl.defaulZone=http://localhost:8761";
    private final int PORT;
    private static String replicaFile;


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

    private String updateHelper(int port) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:" + port + "/read/clear-cache/true";
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public void killReplica() {
        String killCommand = "npx kill-port " + PORT;
        ShellExecutor.create(killCommand).start();
        LOGGER.log("killed replica " + PORT);
    }

    public int getPORT() {
        return PORT;
    }

    public String getReplicaFile() {
        return replicaFile;
    }

    public static void setReplicaFileLocation(String replicaFileLocation){
        replicaFile = replicaFileLocation+"/target/replica-node-0.0.1-SNAPSHOT.jar";
    }


    @Override
    public String toString() {
        return "{ port:" + PORT + "}";
    }
}
