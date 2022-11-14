package org.example.filereader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.filedfactory.FiledFactory;
import org.example.model.JSONWMVariable;
import org.example.valuefactory.ValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.*;
import java.util.*;

@Component
public class FileReaderJSON {
    @Autowired
    FiledFactory filedFactory;
    @Autowired
    ValueFactory valueFactory;
    private Logger logger = LoggerFactory.getLogger(FileReaderJSON.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    public synchronized List<JSONWMVariable> readJsonFile(File file) {
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(file));
            return readJsonObject(jsonObject);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassCastException e) {
            try {
                JSONArray jsonArray = (JSONArray) new JSONParser().parse(new FileReader(file));
                return readJsonArray(jsonArray);
            } catch (IOException | ParseException ex) {
                e.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    public synchronized List<JSONWMVariable> readJsonObject(JSONObject jsonObject) {
        List<JSONWMVariable> jsonwmVariableList = new ArrayList<>();
        try {
            Set<String> keys;
            keys = jsonObject.keySet();
            for (String name : keys) {
                JSONObject mainName = (JSONObject) jsonObject.get(name);
                JSONWMVariable jsonwmVariable = objectMapper.readValue(String.valueOf(mainName), JSONWMVariable.class);
                if (jsonwmVariable != null)
                    jsonwmVariableList.add(jsonwmVariable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonwmVariableList;
    }

    public synchronized List<JSONWMVariable> readJsonArray(JSONArray jsonArray) {
        List<JSONWMVariable> jsonwmVariableList = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject mainName = (JSONObject) jsonArray.get(i);
                JSONWMVariable jsonwmVariable = objectMapper.readValue(String.valueOf(mainName), JSONWMVariable.class);
                if (jsonwmVariable != null)
                    jsonwmVariableList.add(jsonwmVariable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonwmVariableList;
    }
}
