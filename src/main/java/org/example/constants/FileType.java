package org.example.constants;
public enum FileType {
    FILE_TYPE("json");
    private String name;
    private FileType(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
