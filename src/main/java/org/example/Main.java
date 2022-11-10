package org.example;

import org.example.filereader.FileReaderJSON;
import org.example.folderreader.FolderReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class Main {
    public static void main(String args[]) {
        ClassLoader classLoader = Main.class.getClassLoader();
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        FolderReader folderReader= context.getBean(FolderReader.class);
        folderReader.getFileFromFolder(new File(classLoader.getResource("pages").getFile()));
    }
}

