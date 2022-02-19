package com.mohammad.replicanode.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AppLogger {
    private static  Logger LOGGER ;

    private AppLogger(){
        throw new AssertionError();
    }
    private AppLogger(String logName){
       LOGGER = Logger.getLogger(logName);
    }

    public static AppLogger create(String logName){
        return new AppLogger(logName);
    }
    public void log(String message){
        LOGGER.log(Level.INFO,message);
    }
    public  void logError (Exception e){
        LOGGER.log(Level.SEVERE,e.getMessage());
    }
}
