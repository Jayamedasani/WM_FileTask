package org.example.valuefactory;

import org.springframework.stereotype.Component;

@Component
public class ServiceVariable extends FiledValue {
    private final String valueName = "wm.ServiceVariable";

    @Override
    public String getValueName() {
        return valueName;
    }
}
