package org.example.filereader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import org.example.model.JSONWMVariable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileReader;
import java.io.*;
import java.util.*;
public class FileReaderJSON {
    private Logger logger = LoggerFactory.getLogger(FileReaderJSON.class);
    private ObjectMapper objectMapper=new ObjectMapper();
    private SequenceWriter sequenceWriter;

    {
        try {
            sequenceWriter = objectMapper.writerWithDefaultPrettyPrinter().writeValuesAsArray(new FileWriter("jsonFileWM.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void readJsonFile(File file) {
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(file));
            readJsonObject(jsonObject);
        } catch (IOException | ParseException  e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch(ClassCastException e ){
            try {
                JSONArray jsonArray=(JSONArray) new JSONParser().parse(new FileReader(file));
                readJsonArray(jsonArray);
            } catch (IOException | ParseException ex) {
                e.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }
    public void readJsonObject(JSONObject jsonObject){
        Set<String> keys;
        keys = jsonObject.keySet();
        for (String name : keys) {
            JSONObject mainName = (JSONObject) jsonObject.get(name);
            if (mainName.get("category")!=null && mainName.get("category").equals("wm.LiveVariable")) {
                JSONWMVariable jsonwmVariable=new JSONWMVariable((mainName).get("_id"),(mainName).get("name"),(mainName).get("owner"),(mainName).get("package"),(mainName).get("tableName"),(mainName).get("tableType"),(mainName).get("liveSource"),(mainName).get("maxResults"));
                try {
                    sequenceWriter.write(jsonwmVariable);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                logger.info(""+jsonwmVariable);
            }
        }
    }
    public void readJsonArray(JSONArray jsonArray){
        for (int i=0;i<jsonArray.size();i++) {
            JSONObject mainName = (JSONObject) jsonArray.get(i);
            if (mainName.get("category")!=null && mainName.get("category").equals("wm.LiveVariable")) {
                logger.info("inside condition check");
                JSONWMVariable jsonwmVariable=new JSONWMVariable((mainName).get("_id"),(mainName).get("name"),(mainName).get("owner"),(mainName).get("package"),(mainName).get("tableName"),(mainName).get("tableType"),(mainName).get("liveSource"),(mainName).get("maxResults"));
                try {
                    sequenceWriter.write(jsonwmVariable);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
