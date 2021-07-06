package me.tiago0liveira.TiagoUtils.helpers;

import me.tiago0liveira.TiagoUtils.TiagoUtils;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public static File getYMLFile(String fileName) {

        File file = new File(TiagoUtils.getPlugin().getDataFolder(), fileName);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) file.getParentFile().mkdir();
            try {
                file.createNewFile();
                return file;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file;
    }
}
