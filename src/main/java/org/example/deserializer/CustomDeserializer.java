package org.example.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.example.filedfactory.FiledFactory;
import org.example.model.JSONWMVariable;
import org.example.valuefactory.ValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomDeserializer extends StdDeserializer<JSONWMVariable> {
    private ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(CustomDeserializer.class);
    @Autowired
    FiledFactory filedFactory;
    @Autowired
    ValueFactory valueFactory;

    public CustomDeserializer() {
        super(JSONWMVariable.class);
    }

    @Override
    public JSONWMVariable deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        if (node.get("category") != null) {
            String filed = node.get("category").asText();
            if (filed.equals("wm.LiveVariable")) {
                JSONWMVariable jsonwmLiveVariable = new JSONWMVariable(node.get("_id"), node.get("name"), node.get("owner"), node.get("package"), node.get("tableType"), node.get("tableName"), node.get("liveSource"), node.get("maxResults"));
                return jsonwmLiveVariable;
            } else if (filed.equals("wm.ServiceVariable")) {
                JSONWMVariable jsonwmServiceVariable = new JSONWMVariable(node.get("_id"), node.get("name"), node.get("owner"), node.get("package"), node.get("tableType"), node.get("tableName"), node.get("liveSource"), node.get("maxResults"));
                return jsonwmServiceVariable;
            }
        }
        /*if(node.get(filedFactory.getFiled("category").getFiledName())!=null) {
            String filed = node.get(filedFactory.getFiled("category").getFiledName()).asText();
            if (filed.equals(valueFactory.getValue("LiveVariable").getValueName())) {
                JSONWMVariable jsonwmVariable = new JSONWMVariable(node.get("_id"), node.get("name"), node.get("owner"), node.get("package"), node.get("tableType"), node.get("tableName"), node.get("liveSource"), node.get("maxResults"));
                logger.info("" + jsonwmVariable);
                return jsonwmVariable;
            }
        }*/
        return null;
    }
}
