package com.mohammad.masternode.utils;

import com.mohammad.masternode.io.DirectoryCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ShellExecutor {
    private static final AppLogger LOGGER =  AppLogger.create("Shell Executor logger : ");
    private ShellExecutor(){
        throw new AssertionError();
    }


    public static synchronized void runShellCommand(String command) {
        try {
            Process proc = Runtime.getRuntime().exec(command);
            proc.waitFor();

        } catch (IOException | InterruptedException e) {
            LOGGER.logError(e);
        }
    }



}
