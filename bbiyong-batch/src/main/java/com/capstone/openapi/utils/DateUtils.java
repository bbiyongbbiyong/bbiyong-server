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
}
