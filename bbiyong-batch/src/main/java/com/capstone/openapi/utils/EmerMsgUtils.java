package com.capstone.openapi.utils;

import com.capstone.emerMsg.respository.DisasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@RequiredArgsConstructor
public class EmerMsgUtils {

    private final DisasterRepository disasterRepository;

    public String getEmergencyType(String message) {
        Optional<String> topic = disasterRepository.findEnTopicByKrTopic(message);
        if (topic.isPresent())
            return topic.get();
        return "natural_etc";
    }
}
