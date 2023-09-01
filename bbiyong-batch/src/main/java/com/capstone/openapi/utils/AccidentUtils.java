package com.capstone.openapi.utils;

import org.springframework.stereotype.Component;

@Component
public class AccidentUtils {

    public String getAccidentTopic(String type) {
        if (type.equals("A04"))
            return "roadWorks";
        else if (type.equals("A10"))
            return "rallyEvent";
        else if (type.equals("A11") || type.equals("A12") || type.equals("A13"))
            return "roadEtc";
        else
            return "roadAccident";
    }
}
