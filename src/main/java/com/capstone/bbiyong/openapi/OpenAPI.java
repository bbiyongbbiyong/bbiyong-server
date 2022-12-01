package com.capstone.bbiyong.openapi;

import org.json.JSONArray;

import java.io.IOException;
import java.text.ParseException;

public interface OpenAPI {

    void call() throws IOException, ParseException;

    String callOpenAPI() throws IOException;

    JSONArray getJsonArray(String response) throws IOException;

    Object parseDateFormat(Object object) throws ParseException;

    void parseAndSave(JSONArray jsonArray) throws IOException, ParseException;

}
