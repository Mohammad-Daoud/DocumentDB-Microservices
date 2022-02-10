package com.mohammad.masternode.io;


import com.mohammad.masternode.utils.AppLogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import static com.mohammad.masternode.io.DirectoryCreator.getMasterDir;

public class DirectoryRemover {
    private static final AppLogger LOGGER =  AppLogger.create("DirectoryRemover LOGGER");
    private static DirectoryRemover remover;


    private DirectoryRemover(){}

    public static DirectoryRemover getInstance() {
        synchronized (DirectoryRemover.class) {
            if (remover == null)
                remover = new DirectoryRemover();
        }
        return remover;
    }

    // the removing will be done recursively
    public synchronized void deleteDir(String filename) {
        try {
            Files.walk(Path.of(getMasterDir() + "/" + filename))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);

        }catch (IOException e){
            LOGGER.logError(e);
        }
    }
}


