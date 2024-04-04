package org.example.mcibigdatachallenge.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {
    private FileUtil(){}

    public static List<String> readLines(String path){
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
