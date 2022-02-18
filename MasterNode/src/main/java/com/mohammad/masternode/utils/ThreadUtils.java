package com.mohammad.masternode.utils;

public class ThreadUtils {
    private static int count =0;
    private ThreadUtils(){
        throw new AssertionError();
    }
    public static int threadCounter(){
        count += Thread.activeCount();
        return count;
    }
}
