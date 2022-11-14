package org.example;
import org.example.filereader.FileReaderJSON;
import org.example.filewriter.FileWriterJSON;
import org.example.folderreader.FolderReader;
import org.example.model.JSONWMVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.File;
public class Main {
    public static void main(String args[]) {
        Logger logger=LoggerFactory.getLogger(Main.class);
        ClassLoader classLoader = Main.class.getClassLoader();
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        FolderReader folderReader= context.getBean(FolderReader.class);
        folderReader.readFolder(new File(classLoader.getResource("pages").getFile()));
        /*logger.info("file done"+jsonFile.getName());
        if(jsonFile!=null){
           FileReaderJSON fileReaderJSON= context.getBean(FileReaderJSON.class);
           JSONWMVariable jsonwmVariable=fileReaderJSON.readJsonFile(jsonFile);
           logger.info(""+jsonwmVariable);
           if(jsonwmVariable!=null){
               logger.info("to writer");
               FileWriterJSON.getInstance().writeJsonFile(jsonwmVariable);
           }
        }*/
    }
}

