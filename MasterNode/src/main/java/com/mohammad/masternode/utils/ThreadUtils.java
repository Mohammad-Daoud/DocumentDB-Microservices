package com.mohammad.masternode.utils;

public class ThreadUtils {
    private ThreadUtils(){
        throw new AssertionError();
    }
    public static int threadCounter(){
        return Thread.activeCount();
    }
}
