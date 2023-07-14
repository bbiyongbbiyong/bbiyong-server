package com.capstone.openapi;

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
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.URLEncoder.encode;

@Component
@RequiredArgsConstructor
public class XYChangeOpenAPI {

    @Value("${app.change-to-address-key}")
    private String CHANGE_TO_ADDRESS_KEY;
    @Value("${app.tm-consumer-key}")
    private String TM_CONSUMER_KEY;
    @Value("${app.tm-consumer-secret}")
    private String TM_CONSUMER_SECRET;
    private static final String SRC_COORDINATE_CODE = "EPSG:5181";
    private static final String DST_COORDINATE_CODE = "EPSG:4326";
    private static final String COORDINATE_CHANGE_SERVICE = "address";
    private static final String COORDINATE_CHANGE_REQUEST = "GetAddress";
    private static final String COORDINATE_CHANGE_TYPE = "both";

    // TM 좌표 -> 위경도 -> 구 단위 (메인 메서드)
    public String XYChangeToAddress(BigDecimal tmX, BigDecimal tmY) throws IOException, ParseException {
        JSONObject objToken = getJsonObject(callATOpenAPI());
        String accessToken = (String) objToken.get("accessToken");

        StringBuilder urlBuilder = new StringBuilder("https://sgisapi.kostat.go.kr/OpenAPI3/transformation/transcoord.json") /*URL*/
                .append("?" + encode("accessToken","UTF-8") + "=" + accessToken)
                .append("&" + encode("src","UTF-8") + "=" + encode(SRC_COORDINATE_CODE, "UTF-8"))
                .append("&" + encode("dst","UTF-8") + "=" + encode(DST_COORDINATE_CODE, "UTF-8"))
                .append("&" + encode("posX","UTF-8") + "=" + tmX)
                .append("&" + encode("posY","UTF-8") + "=" + tmY);

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

        JSONObject objPos = getJsonObject(sb.toString());
        Double posX = (Double) objPos.get("posX");
        Double posY = (Double) objPos.get("posY");

        return callAddressOpenAPI(posX, posY);
    }

    // 위경도 -> 구 단위 API
    private String callAddressOpenAPI(Double x, Double y) throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder("http://api.vworld.kr/req/address") /*URL*/
                .append("?" + encode("service","UTF-8") + "=" + encode(COORDINATE_CHANGE_SERVICE, "UTF-8"))
                .append("&" + encode("request","UTF-8") + "=" + encode(COORDINATE_CHANGE_REQUEST, "UTF-8"))
                .append("&" + encode("point","UTF-8") + "=" + x + "," + y)
                .append("&" + encode("type","UTF-8") + "=" + encode(COORDINATE_CHANGE_TYPE, "UTF-8"))
                .append("&" + encode("key","UTF-8") + "=" + encode(CHANGE_TO_ADDRESS_KEY, "UTF-8"));

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

        String address = parseAddress(sb);

        return address;
    }

    // callAddressOpenAPI에서의 json 파싱을 위한 메서드
    private String parseAddress(StringBuilder sb) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(sb.toString());
        JSONObject jsonResponse = (JSONObject) jsonObject.get("response");
        JSONArray jsonArray = (JSONArray) jsonResponse.get("result");
        JSONObject objAddress = (JSONObject) jsonArray.get(0);
        JSONObject objStructure = (JSONObject) objAddress.get("structure");

        String address = (String) objStructure.get("level2");

        return address;
    }

    // XYChangeToAddress에서의 json 파싱을 위한 메서드
    private JSONObject getJsonObject(String response) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(response);
        JSONObject obj = (JSONObject)jsonObject.get("result");

        return obj;
    }

    // TM좌표 -> 위경도 API에 필요한 ACCESS TOKEN을 받아오는 API
    private String callATOpenAPI() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("https://sgisapi.kostat.go.kr/OpenAPI3/auth/authentication.json")
                .append("?" + encode("consumer_key","UTF-8") + "=" + encode(TM_CONSUMER_KEY, "UTF-8"))
                .append("&" + encode("consumer_secret","UTF-8") + "=" + encode(TM_CONSUMER_SECRET, "UTF-8"));

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
}
