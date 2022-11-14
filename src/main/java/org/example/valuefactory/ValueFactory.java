package org.example.valuefactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValueFactory {
    @Autowired
    LiveVariable liveVariable;
    @Autowired
    ServiceVariable serviceVariable;

    public FiledValue getValue(String valueName) {
        if (valueName.equalsIgnoreCase("LiveVariable")) {
            return liveVariable;
        } else if (valueName.equalsIgnoreCase("ServiceVariable")) {
            return serviceVariable;
        }
        return null;
    }
}
