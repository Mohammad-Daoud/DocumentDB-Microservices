package com.mohammad.masternode.utils;

import java.io.IOException;


public class ShellExecutor extends Thread {
    private static final AppLogger LOGGER = AppLogger.create("Shell Executor logger : ");
    private final String COMMAND;

    private ShellExecutor(String command) {
        this.COMMAND = command;
    }

    public static ShellExecutor create(String command) {
        return new ShellExecutor(command);
    }

    @Override
    public void run() {
        runShellCommand(COMMAND);
    }

    private static void runShellCommand(String command) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
            builder.start();
            LOGGER.log(command + " command run successfully");

        } catch (IOException e) {
            LOGGER.logError(e);
        }
    }


}
