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

public class DirectoryLoader {

    public static final File MASTER_DIR = new File("C:/Users/mdss4/Documents/Atypon/DocumentDB/MasterNode/storage/master-node");
    private static final AppLogger LOGGER =  AppLogger.create("Directory loader logger") ;

    private DirectoryLoader(){}

    public static List<String> loadDirs(File parent, int level) {
        List<File> dirs = new ArrayList<File>();
        List<String> finalResult = new ArrayList<>();
        File[] files = parent.listFiles();
        if (files == null) return finalResult; // empty dir

        for (File f : files) {
            if (f.isDirectory()) {
                if (level == 0) dirs.add(f);
                else if (level > 0) finalResult.addAll(loadDirs(f, level - 1));
            }
        }

        dirs.forEach(d -> finalResult.add(d.getName()));
        return finalResult;
    }

/*    public String findDir(File root, String name) {
        if (root.getName().equals(name)) {
            return root.getName();
        }
        File[] files = root.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    String myResult = findDir(f, name);
                    if (myResult != null) {
                        return myResult;
                    }
                }
            }
        }
        return null;
    }*/

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

    public static String readFile(String filename) {
        String content ="";
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
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
}
