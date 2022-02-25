package com.mohammad.replicanode.io;

import com.mohammad.replicanode.utils.AppLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static  File masterDir ;
    private static final AppLogger LOGGER =  AppLogger.create("Directory loader logger") ;

    private FileManager(){}

    public static List<File> loadDirs(File parent, int level) {
        List<File> dirs = new ArrayList<File>();
        File[] files = parent.listFiles();
        if (files == null) return dirs; // empty dir

        for (File f : files) {
            if (f.isDirectory()) {
                if (level == 0) dirs.add(f);
                else if (level > 0) dirs.addAll(loadDirs(f, level - 1));
            }
        }
        return dirs;
    }

    public static List<String> listFiles (String filePath){
        List<String> results = new ArrayList<String>();
        try {
            Files.walk(Paths.get(filePath))
                    .filter(Files::isRegularFile)
                    .forEach(f -> results.add(f.getFileName().toString()));
        } catch (IOException e) {
            LOGGER.logError(e);
        }
        return results;
    }

    public static String readFile(String filePath) {
        String content ="";
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
                content = readFileHelper(fileReader);
        } catch (IOException e) {
            LOGGER.logError(e);
        }
        return content;
    }

    private static String readFileHelper(BufferedReader fileReader) throws IOException{
        StringBuilder fileContent = new StringBuilder();
        String lineContent;
        while ((lineContent = fileReader.readLine()) != null) {
            fileContent.append(lineContent);
        }
        return fileContent.toString();
    }

    public static File getMasterDir() {
        return masterDir;
    }

    public static File getUsersDir(){
        return new File(getMasterDir().getAbsolutePath());
    }

    public static File getDatabaseDir(){
        return new File(getMasterDir()+"/storage/master-node");
    }

    public static void setMaster(String masterDir) {
        FileManager.masterDir = new File(masterDir);
    }
}
