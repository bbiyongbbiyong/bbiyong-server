package com.capstone.bbiyong.openapi;

import com.capstone.bbiyong.accident.domain.Accident;
import com.capstone.bbiyong.accident.repository.AccidentRepository;
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
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.net.URLEncoder.encode;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccidentOpenAPI implements OpenAPI {

    private final AccidentRepository accidentRepository;

    @Value("${app.seoul-openapi-key}")
    private String SEOUL_OPENAPI_KEY;

    private static final String SEOUL_OPENAPI_URL = "http://openapi.seoul.go.kr:8088";
    private static final String REQUEST_FILE_TYPE = "xml";
    private static final String SERVICE_NAME = "AccInfo";
    private static final String START_LOCATION = "1"; /*요청시작위치*/
    private static final String END_LOCATION = "5"; /*요청종료위치*/

    @Override
    public void call() throws IOException, ParseException {
        String response = callOpenAPI();
        JSONArray jsonArray = getJsonArray(response);
        parseAndSave(jsonArray);
    }

    @Override
    public String callOpenAPI() throws IOException {
        StringBuilder requestUrl = new StringBuilder(SEOUL_OPENAPI_URL)
                .append("/" + encode(SEOUL_OPENAPI_KEY, "UTF-8"))
                .append("/" + encode(REQUEST_FILE_TYPE, "UTF-8"))
                .append("/" + encode(SERVICE_NAME, "UTF-8"))
                .append("/" + encode(START_LOCATION, "UTF-8"))
                .append("/" + encode(END_LOCATION, "UTF-8"));

        URL url = new URL(requestUrl.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        else
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null)
            response.append(line);

        rd.close();
        conn.disconnect();

        log.info("response(XML): {}", response);
        return response.toString();
    }

    // XML -> JSONArray or JSON -> JSONArray 바꿈
    @Override
    public JSONArray getJsonArray(String response) throws IOException {
        JSONObject xml2JsonObj = XML.toJSONObject(response);
        JSONObject jsonObject = (JSONObject) xml2JsonObj.get(SERVICE_NAME);
        JSONArray jsonArray = (JSONArray) jsonObject.get("row");

        log.info("response(JSON): {}", jsonArray);
        return jsonArray;
    }

    @Override
    public void parseAndSave(JSONArray jsonArray) throws IOException, ParseException {

        JSONObject jsonObject;

        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);

            Integer accId = (Integer) jsonObject.get("acc_id"); /*돌발 아이디*/
            Integer intStartDate = (Integer) jsonObject.get("occr_date"); /*발생 일자*/
            Integer intEndDate = (Integer) jsonObject.get("exp_clr_date"); /*종료 일자*/
            String accidentType = (String) jsonObject.get("acc_type"); /*공사인지 사고인지 구별*/
            BigDecimal xMap = (BigDecimal) jsonObject.get("grs80tm_x"); /*X 좌표*/
            BigDecimal yMap = (BigDecimal) jsonObject.get("grs80tm_y"); /*Y 좌표*/
            String accidentInfo = ((String) jsonObject.get("acc_info")).replace("\r", "\n"); /*상세 정보*/

            Date startDate = parseDateFormat(intStartDate);
            Date endDate = parseDateFormat(intEndDate);

            Accident accident = Accident.builder()
                    .accId(accId)
                    .startDate(startDate)
                    .endDate(endDate)
                    .accidentType(accidentType)
                    .accidentInfo(accidentInfo)
                    .xMap(String.valueOf(xMap))
                    .yMap(String.valueOf(yMap))
                    .build();

            accidentRepository.save(accident);
        }
    }

    // Integer "yyyymmdd" -> Date class 변환
    @Override
    public Date parseDateFormat(Object intDate) throws ParseException {
        SimpleDateFormat fm = new SimpleDateFormat("yyyymmdd");
        return fm.parse(String.valueOf(intDate));
    }

}