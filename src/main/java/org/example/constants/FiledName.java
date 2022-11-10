package org.example.constants;
public enum FiledName {
    WMATTRIBUTE("category");
    private String name;
    private FiledName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
