package com.mohammad.masternode.io;

import com.mohammad.masternode.utils.AppLogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DirectoryCreator {
    private static final File MASTER_DIR = new File("C:/Users/mdss4/Documents/Atypon/DocumentDB/MasterNode/storage/master-node");
    private final AppLogger LOGGER =  AppLogger.create("DirectoryCreator logger");
    private static volatile DirectoryCreator creator;

    private DirectoryCreator() {
    }

    public static DirectoryCreator getInstance() {
        synchronized (DirectoryCreator.class) {
            if (creator == null)
                creator = new DirectoryCreator();
        }
        return creator;
    }

    public synchronized void createDirectory(String dirPath) {
        File file = new File(MASTER_DIR + "/" + dirPath);
        file.mkdir();
    }

    public synchronized void overrideWriteFile(String dirPath, String content) {

        File file = new File(dirPath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            LOGGER.logError(e);
        }
    }

    public synchronized void writeFile(String dirPath, String content) {
        File file = new File(MASTER_DIR+"/"+dirPath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(content);
        } catch (IOException e) {
            LOGGER.logError(e);
        }
    }

    public static File getMasterDir() {
        return MASTER_DIR;
    }
}
