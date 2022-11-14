package org.example.folderreader;

import org.example.constants.FileType;
import org.example.filereader.FileReaderJSON;
import org.example.filewriter.FileWriterLiveVariableJSON;
import org.example.filewriter.FileWriterServiceVariableJSON;
import org.example.model.JSONWMVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class FolderReader {
    @Autowired
    FileReaderJSON fileReaderJSON;
    private Logger logger = LoggerFactory.getLogger(FolderReader.class);

    public synchronized void readFolder(File folderName) {
        for (File file : folderName.listFiles()) {
            if (file.isDirectory()) {
                readFolder(file);
            } else if ((file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase()).equalsIgnoreCase(String.valueOf(FileType.JSON))) {
                List<JSONWMVariable> jsonwmVariableList = fileReaderJSON.readJsonFile(file);
                for (JSONWMVariable jsonwmVariable : jsonwmVariableList) {
                    if (String.valueOf(jsonwmVariable.get_id()).contains("wm.ServiceVariable")) {
                        FileWriterServiceVariableJSON.getInstance().writeJsonFile(jsonwmVariable);
                    } else {
                        FileWriterLiveVariableJSON.getInstance().writeJsonFile(jsonwmVariable);
                    }
                }
            }
        }
    }
}
