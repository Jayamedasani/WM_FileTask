package org.example;

import org.example.folderreader.FolderReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class Main {


    public static void main(String args[]) {
        ClassLoader classLoader = Main.class.getClassLoader();
        new FolderReader().getFileFromFolder(new File(classLoader.getResource("pages").getFile()));
    }
}

