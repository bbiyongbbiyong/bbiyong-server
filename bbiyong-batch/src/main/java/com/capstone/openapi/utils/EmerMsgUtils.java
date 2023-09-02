package com.capstone.openapi.utils;

import com.capstone.emerMsg.domain.Disaster;
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

    public String getEmergencyTopic(String message) {
        Optional<Disaster> topic = disasterRepository.findEnTopicByKrTopic(message);
        if (topic.isPresent())
            return topic.get().getEnTopic();
        return "naturalEtc";
    }

    public String getEmergencyType(String message) {
        Optional<Disaster> type = disasterRepository.findEnTopicByKrTopic(message);
        if (type.isPresent())
            return type.get().getType();
        return "natural";
    }

    public List<Long> separateLocation(String locationId) {
        String [] locations = locationId.split(",");
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < locations.length; i++) {
            Long num = Long.parseLong(locations[i]);
            num = num - 135;
            result.add(num);
        }
        return (result);
    }

}
