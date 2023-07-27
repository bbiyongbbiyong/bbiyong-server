package com.capstone.openapi.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateUtils {

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
