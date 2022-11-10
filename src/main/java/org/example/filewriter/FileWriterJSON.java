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
public class FileWriterJSON {
    private static FileWriterJSON fileWriterJSON;
    private static SequenceWriter sequenceWriter;
    private static ObjectMapper objectMapper=new ObjectMapper();
    private Logger logger= LoggerFactory.getLogger(FileWriterJSON.class);
    private FileWriterJSON(){}
    public static FileWriterJSON getInstance() {
        try {
            if(fileWriterJSON==null) {
                sequenceWriter = objectMapper.writerWithDefaultPrettyPrinter().writeValuesAsArray(new FileWriter("jsonFileWM.json"));
                fileWriterJSON=new FileWriterJSON();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileWriterJSON;
    }
    public void writeJsonFile(JSONWMVariable jsonwmVariable){
        synchronized (this) {
            try {
                sequenceWriter.write(jsonwmVariable);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
