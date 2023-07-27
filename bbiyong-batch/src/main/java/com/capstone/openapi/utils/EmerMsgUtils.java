package com.capstone.openapi.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmerMsgUtils {
    public Date StringToDate(String stringDate) throws ParseException {
        SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = fm.parse(stringDate);
        return date;
    }

    // TODO : 시작 일자 변경 가능. 현재는 하루 전부터 데이터부터 조회
    public String getStartDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date date = new Date(cal.getTimeInMillis());
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return transFormat.format(date);
    }

    public Date getEndDate(Date startDateTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDateTime);
        cal.add(Calendar.DATE, 2);
        return cal.getTime();
    }

    public String getEmergencyType(String message) {
        Map<String, String> keywordToType = new HashMap<>();
        keywordToType.put("태풍", "typhoon");
        keywordToType.put("미세먼지", "fine_dust");
        keywordToType.put("건조", "dry");
        keywordToType.put("대조기", "spring_tide");
        keywordToType.put("산불", "forest_fires");
        keywordToType.put("가뭄", "drought");
        keywordToType.put("산사태", "landslide");
        keywordToType.put("대설", "heavy_snow");
        keywordToType.put("홍수", "flood");
        keywordToType.put("호우", "downpour");
        keywordToType.put("폭염", "heat_wave");
        keywordToType.put("안개", "fog");
        keywordToType.put("풍랑", "wind_wave");
        keywordToType.put("지진해일", "tsunami");
        keywordToType.put("지진", "earthquake");
        keywordToType.put("한파", "cold_wave");
        keywordToType.put("황사", "yellow_dust");
        keywordToType.put("강풍", "gale");
        keywordToType.put("교통통제", "traffic_control");
        keywordToType.put("화재", "fire_alert");
        keywordToType.put("붕괴", "collapse");
        keywordToType.put("폭발", "explosion");
        keywordToType.put("교통사고", "traffic_accident");
        keywordToType.put("환경오염사고", "env_pollution");
        keywordToType.put("에너지", "energy");
        keywordToType.put("통신", "communication");
        keywordToType.put("의료", "medical");
        keywordToType.put("수도", "water_alert");
        keywordToType.put("전염병", "epidemic");
        keywordToType.put("코로나", "epidemic");
        keywordToType.put("접종", "epidemic");
        keywordToType.put("백신", "epidemic");
        keywordToType.put("정전", "blackout");
        keywordToType.put("가스", "gas");
        keywordToType.put("실종", "missing");
        keywordToType.put("배회", "missing");
        keywordToType.put("목격", "missing");
        keywordToType.put("교통", "traffic");

        for (Map.Entry<String, String> entry : keywordToType.entrySet()) {
            if (message.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        return "natural_etc";
    }
}
