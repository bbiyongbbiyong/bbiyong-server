package com.capstone.bbiyong.openapi;

import com.capstone.bbiyong.emerMsg.domain.EmerMsg;
import com.capstone.bbiyong.emerMsg.service.EmerMsgService;
import com.capstone.bbiyong.location.domain.Location;
import com.capstone.bbiyong.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static java.net.URLEncoder.encode;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmerMsgOpenAPI implements OpenAPI {

    private final EmerMsgService emerMsgService;
    private final LocationRepository locationRepository;

    @Value("${app.emergency-message-key}")
    private String EMERGENCY_MESSAGE_KEY;
    private static final String REQUEST_FILE_TYPE = "xml";
    private static final String SERVICE_NAME = "DisasterMsg2";
    private static final String START_LOCATION = "1"; /*요청시작위치*/
    private static final String END_LOCATION = "15"; /*요청종료위치*/
    private static final String LOCATION = "서울특별시";


    @Override
    public void call() throws IOException, ParseException {
        String response = callOpenAPI();
        org.json.JSONArray jsonArray = getJsonArray(response);
        parseAndSave(jsonArray);
    }

    @Override
    public String callOpenAPI() throws IOException {

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1741000/DisasterMsg4/getDisasterMsg2List") /*URL*/
            .append("?" + encode("serviceKey","UTF-8") + "=" + EMERGENCY_MESSAGE_KEY) /*Service Key*/
            .append("&" + encode("pageNo","UTF-8") + "=" + encode(START_LOCATION, "UTF-8")) /*페이지번호*/
            .append("&" + encode("numOfRows","UTF-8") + "=" + encode(END_LOCATION, "UTF-8")) /*한 페이지 결과 수*/
            .append("&" + encode("type","UTF-8") + "=" + encode(REQUEST_FILE_TYPE, "UTF-8")) /*호출문서 형식*/
            .append("&" + encode("create_date","UTF-8") + "=" + encode(getStartDate(), "UTF-8")) /*생성일시(포함하여 큰 데이터 조회)*/
            .append("&" + encode("location_name","UTF-8") + "=" + encode(LOCATION, "UTF-8")); /*수신지역 이름*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");

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

    // XML -> JSONArray or JSON -> JSONArray 바꿈
    @Override
    public JSONArray getJsonArray(String response) {
        JSONObject xml2JsonObj = XML.toJSONObject(response);
        JSONObject jsonObject = (JSONObject) xml2JsonObj.get(SERVICE_NAME);
        JSONArray jsonArray = (JSONArray) jsonObject.get("row");

        return jsonArray;
    }

    @Override
    public void parseAndSave(JSONArray jsonArray) throws ParseException {

        JSONObject jsonObject;

        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);

            String openapiId = String.valueOf(jsonObject.get("md101_sn"));
            String locationId = String.valueOf(jsonObject.get("location_id"));
            String message = String.valueOf(jsonObject.get("msg"));
            String strStartDate = String.valueOf(jsonObject.get("create_date"));

            Date startDate = StringToDate(strStartDate);
            Date endDate = getEndDate(startDate);

            if (!locationId.contains(",")) {
                Long id = Long.parseLong(locationId) - 135;
                Optional<Location> OptLocation = locationRepository.findById(id);
                if (OptLocation.isPresent()) {
                    EmerMsg emerMsg = EmerMsg.builder()
                            .openapiId(Long.parseLong(openapiId))
                            .message(message)
                            .startDate(startDate)
                            .endDate(endDate)
                            .location(OptLocation.get())
                            .build();

                    emerMsgService.addEmerMsg(emerMsg);
                }
            }
        }
    }

    private Date StringToDate (String stringDate) throws ParseException {
        SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = fm.parse(stringDate);
        return date;
    }

    // TODO : 시작 일자 변경 가능. 현재는 하루 전부터 데이터부터 조회
    private String getStartDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date date = new Date(cal.getTimeInMillis());
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return transFormat.format(date);
    }

    public Date getEndDate (Date startDateTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDateTime);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }
}
