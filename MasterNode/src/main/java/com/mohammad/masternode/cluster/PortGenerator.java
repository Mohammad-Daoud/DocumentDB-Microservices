package com.mohammad.masternode.cluster;

public class PortGenerator {

    private static volatile PortGenerator instance;
    private int port;

    private PortGenerator() {
        this.port = 8999;
    }

    public static PortGenerator getInstance() {
        synchronized (PortGenerator.class) {
            if (instance == null)
                instance = new PortGenerator();
        }
        return instance;
    }

    public int generateNewPort(){
        return ++port;
    }

    public int getPort() {
        return port;
    }
}
