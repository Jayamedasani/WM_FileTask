package org.example.constants;
public enum VariableType {
    WMLIVEVARIABLE("wm.LiveVariable"),
    WMSERVICEVARIABLE("wm.ServiceVariable");
    private String name;
    private VariableType(String name){
        this.name=name;
    }
    public String getValue(){
        return name;
    }
}
