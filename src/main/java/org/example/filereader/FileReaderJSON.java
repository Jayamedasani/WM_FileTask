package org.example.filereader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.constants.FiledName;
import org.example.constants.VariableType;
import org.example.filewriter.FileWriterJSON;
import org.example.model.JSONWMVariable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.io.*;
import java.util.*;
@Component
public class FileReaderJSON {
    private Logger logger = LoggerFactory.getLogger(FileReaderJSON.class);
    private ObjectMapper objectMapper=new ObjectMapper();
    public void readJsonFile(File file) {
        synchronized (this) {
            try {
                JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(file));
                readJsonObject(jsonObject, file);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (ClassCastException e) {
                try {
                    JSONArray jsonArray = (JSONArray) new JSONParser().parse(new FileReader(file));
                    readJsonArray(jsonArray, file);
                } catch (IOException | ParseException ex) {
                    e.printStackTrace();
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    public void readJsonObject(JSONObject jsonObject,File file) {
        synchronized (this) {
            try {
                Set<String> keys;
                keys = jsonObject.keySet();
                for (String name : keys) {
                    JSONObject mainName = (JSONObject) jsonObject.get(name);
                    if ((mainName.get(FiledName.WMATTRIBUTE.getName()) != null && mainName.get(FiledName.WMATTRIBUTE.getName()).equals(VariableType.WMLIVEVARIABLE.getValue()))) {
                        JSONWMVariable jsonwmVariable = objectMapper.readValue(String.valueOf(mainName), JSONWMVariable.class);
                        FileWriterJSON.getInstance().writeJsonFile(jsonwmVariable);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void readJsonArray(JSONArray jsonArray,File file) {
        synchronized (this) {
            try {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject mainName = (JSONObject) jsonArray.get(i);
                    if (mainName.get("category") != null && mainName.get("category").equals("wm.LiveVariable")) {
                        JSONWMVariable jsonwmVariable = objectMapper.readValue(String.valueOf(mainName), JSONWMVariable.class);
                        FileWriterJSON.getInstance().writeJsonFile(jsonwmVariable);
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
