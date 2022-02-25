package com.mohammad.masternode.io;


import com.mohammad.masternode.exception.NotFoundException;
import com.mohammad.masternode.utils.AppLogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import static com.mohammad.masternode.io.DirectoryCreator.getMasterDir;
import static java.rmi.server.LogStream.log;

public class DirectoryRemover {
    private static final AppLogger LOGGER = AppLogger.create("DirectoryRemover LOGGER");
    private static DirectoryRemover remover;


    private DirectoryRemover() {
    }

    public static DirectoryRemover getInstance() {
        if (remover == null) {
            synchronized (DirectoryRemover.class) {
                if (remover == null)
                    remover = new DirectoryRemover();
            }
        }
        return remover;
    }

    // the removing will be done recursively
    public synchronized void deleteDir(String dirPath) {
        try {
            Files.walk(Path.of(getMasterDir() + "/" + dirPath))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);

        } catch (IOException e) {
            LOGGER.logError(e);
        }
    }

    public synchronized void deleteFile(String filePath) {
        File fileToDelete = new File(getMasterDir() + "/" + filePath);

        if (fileToDelete.delete())
            LOGGER.log("file" + fileToDelete.getName() + " deleted successfully !! ");
        else
            LOGGER.logError(new NotFoundException("directory is NOT FOUND !!"));
    }


}


