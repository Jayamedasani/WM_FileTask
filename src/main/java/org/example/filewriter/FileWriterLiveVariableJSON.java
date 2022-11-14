package org.example.filewriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import org.example.model.JSONWMVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class FileWriterLiveVariableJSON {
    private static FileWriterLiveVariableJSON fileWriterJSON;
    private static SequenceWriter sequenceWriter;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(FileWriterLiveVariableJSON.class);

    private FileWriterLiveVariableJSON() {
    }

    public static FileWriterLiveVariableJSON getInstance() {
        try {
            if (fileWriterJSON == null) {
                sequenceWriter = objectMapper.writerWithDefaultPrettyPrinter().writeValuesAsArray(new FileWriter("jsonFileWMLiveVariable.json"));
                fileWriterJSON = new FileWriterLiveVariableJSON();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileWriterJSON;
    }

    public synchronized void writeJsonFile(JSONWMVariable jsonwmVariable) {
        try {
            sequenceWriter.write(jsonwmVariable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
