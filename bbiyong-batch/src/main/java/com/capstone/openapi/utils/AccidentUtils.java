package com.capstone.openapi.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class AccidentUtils {

    // Integer "yyyyMMdd" -> Date class 변환
    public Date parseDateFormat(Object intDate, int intTime) throws ParseException {
        String strTime = Integer.toString(intTime);
        String strHour, strMin;

        if (strTime.length() == 3 || strTime.length() == 5) {
            strHour = strTime.substring(0, 1);
            strMin = strTime.substring(1, 3);
        }
        else if (strTime.length() == 1) {
            strHour = "0";
            strMin = "0";
        }
        else {
            strHour = strTime.substring(0, 2);
            strMin = strTime.substring(2, 4);
        }

        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        Date tempDate = fm.parse(String.valueOf(intDate));
        cal.setTime(tempDate);
        cal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(strHour));
        cal.add(Calendar.MINUTE, Integer.parseInt(strMin));

        return cal.getTime();
    }
}
