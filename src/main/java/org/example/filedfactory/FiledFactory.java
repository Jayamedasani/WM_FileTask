package org.example.filedfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FiledFactory {
    @Autowired
    CategoryFiled categoryFiled;

    public Filed getFiled(String filedName) {
        if (filedName.equals("category")) {
            return categoryFiled;
        }
        return null;
    }
}
