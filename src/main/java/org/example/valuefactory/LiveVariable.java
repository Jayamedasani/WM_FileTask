package org.example.valuefactory;

import org.springframework.stereotype.Component;

@Component
public class LiveVariable extends FiledValue {
    String valueName = "wm.LiveVariable";

    @Override
    public String getValueName() {
        return valueName;
    }
}
