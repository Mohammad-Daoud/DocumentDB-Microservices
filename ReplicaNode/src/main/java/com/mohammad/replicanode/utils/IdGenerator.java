package com.mohammad.replicanode.utils;

import java.util.ArrayList;
import java.util.List;

public class IdGenerator {

    private static List<Integer> idGroup;

    private static IdGenerator idGenerator;

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        if (idGenerator == null) {
            idGenerator = new IdGenerator();
            idGroup = new ArrayList<>();
        }
        return idGenerator;

    }


    public void putKey(int key) {
        idGroup.add(key);
    }


    public synchronized int generateKey() {
        int key = 0;
        while (idGroup.contains(key)) {
            key++;
        }
        putKey(key);
        return key;
    }


    public synchronized void deleteKey(int key) {
        idGroup.removeIf(integer -> integer == key);
    }

    public boolean isReserved(int key) {
        return idGroup.contains(key);
    }


}

