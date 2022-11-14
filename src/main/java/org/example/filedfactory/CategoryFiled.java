package org.example.filedfactory;

import org.springframework.stereotype.Component;

@Component
public class CategoryFiled implements Filed{
    private final String filedName="category";
    @Override
    public String getFiledName() {
        return filedName;
    }
}
