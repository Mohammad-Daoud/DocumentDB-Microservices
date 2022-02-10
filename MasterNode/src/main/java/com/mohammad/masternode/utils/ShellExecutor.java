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

            // Read the output
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }

            proc.waitFor();

        } catch (IOException | InterruptedException e) {
            LOGGER.logError(e);
        }
    }


    public static void writeReplicaBash(int port){
        final String BASH_PATH = "replicaShell.sh";
        String bashContent = "#!/bin/sh\n" +
                "cd 'C:/Users/mdss4/Documents/Atypon/DocumentDB/ReplicaNode/target'\n" +
                "java -jar replica-node-0.0.1-SNAPSHOT.jar --server.port="+port;
        DirectoryCreator.getInstance().overrideWriteFile(BASH_PATH,bashContent);
    }
}
