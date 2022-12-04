package com.capstone.bbiyong.openapi;

import com.capstone.bbiyong.emerMsg.domain.EmerMsg;
import com.capstone.bbiyong.emerMsg.service.EmerMsgService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.net.URLEncoder.encode;

@Component
@RequiredArgsConstructor
public class EmerMsgOpenAPI {

    private final EmerMsgService emerMsgService;

    @Value("${app.emergency-message-key}")
    private String EMERGENCY_MESSAGE_KEY;

    public void parseAndSave() throws IOException, ParseException, java.text.ParseException {

        String parseAPI = callOpenAPI();
        JSONArray jsonArray = getJsonArray(parseAPI);

        JSONObject jsonObject;

        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);

            String msgId = (String) jsonObject.get("md101_sn");
            String locationId = (String) jsonObject.get("location_id");
            String locationName = (String) jsonObject.get("location_name");
            String msg = (String) jsonObject.get("msg");
            String date = (String) jsonObject.get("create_date");

            if (!locationId.contains(",")) {
                EmerMsg emerMsg = EmerMsg.builder()
                        .locationId(Integer.valueOf(locationId))
                        .locationName(locationName)
                        .emerMsgId(Long.valueOf(msgId))
                        .msg(msg)
                        .date(StringToDate(date))
                        .build();

                emerMsgService.addEmerMsg(emerMsg);
            }
        }
    }

    public String callOpenAPI() throws IOException {
        String createDate = RequestDate();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1741000/DisasterMsg4/getDisasterMsg2List") /*URL*/
            .append("?" + encode("serviceKey","UTF-8") + "=" + EMERGENCY_MESSAGE_KEY) /*Service Key*/
            .append("&" + encode("pageNo","UTF-8") + "=" + encode("1", "UTF-8")) /*페이지번호*/
            .append("&" + encode("numOfRows","UTF-8") + "=" + encode("10", "UTF-8")) /*한 페이지 결과 수*/
            .append("&" + encode("type","UTF-8") + "=" + encode("json", "UTF-8")) /*호출문서 형식*/
            .append("&" + encode("create_date","UTF-8") + "=" + encode(createDate, "UTF-8")) /*생성일시(포함하여 큰 데이터 조회)*/
            .append("&" + encode("location_name","UTF-8") + "=" + encode("서울특별시", "UTF-8")); /*수신지역 이름*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        return sb.toString();
    }

    public JSONArray getJsonArray(String result) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(result);
        JSONArray parseDisasterMsg2 = (JSONArray) obj.get("DisasterMsg2");
        JSONObject parseRow = (JSONObject) parseDisasterMsg2.get(1);
        JSONArray jsonArray = (JSONArray) parseRow.get("row");

        return jsonArray;
    }

    public Date StringToDate (String string) throws java.text.ParseException {
        SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = fm.parse(string);
        return date;
    }

    public String RequestDate () {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date date = new Date(cal.getTimeInMillis());
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        return transFormat.format(date);
    }
}
