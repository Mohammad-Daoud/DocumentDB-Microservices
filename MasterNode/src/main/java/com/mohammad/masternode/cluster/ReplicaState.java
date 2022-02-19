package com.mohammad.masternode.cluster;

public class ReplicaState {

    private static volatile ReplicaState instance;

    private int state;

    private ReplicaState() {
        this.state = 1;
    }

    public static ReplicaState getInstance() {
        synchronized (ReplicaState.class) {
            if (instance == null)
                instance = new ReplicaState();
        }
        return instance;
    }

    public void increaseState() {
        ++state;
    }

    public int getState() {
        return state;
    }
}
