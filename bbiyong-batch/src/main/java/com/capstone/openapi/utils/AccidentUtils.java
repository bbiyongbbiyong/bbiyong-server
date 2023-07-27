package com.capstone.openapi.utils;

import org.springframework.stereotype.Component;

@Component
public class AccidentUtils {

    public String getAccidentTopic(String type) {
        if (type == "A04")
            return "road_works";
        else if (type == "A10")
            return "rally_event";
        else if (type == "A11" || type == "A12" || type == "A13")
            return "road_etc";
        else
            return "road_accident";
    }
}
