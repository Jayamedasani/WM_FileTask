package org.example.folderreader;
import org.example.constants.FileType;
import org.example.filereader.FileReaderJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
@Component
public class FolderReader {
    private Logger logger= LoggerFactory.getLogger(FolderReader.class);
//    FileReaderJSON fileReaderJSON=new FileReaderJSON();
    @Autowired
    FileReaderJSON fileReaderJSON;
    public void getFileFromFolder(File folderName) {
        synchronized (this) {
            for (File file : folderName.listFiles()) {
                if (file.isDirectory()) {
                    getFileFromFolder(file);
                } else if ((file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase()).equals(FileType.FILE_TYPE.getName())) {
                    fileReaderJSON.readJsonFile(file);
                }
            }
        }
    }
}
