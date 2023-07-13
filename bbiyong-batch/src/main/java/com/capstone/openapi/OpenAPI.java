package com.capstone.openapi;

import org.json.JSONArray;

import java.io.IOException;
import java.text.ParseException;

public interface OpenAPI {

    void call() throws IOException, ParseException, org.json.simple.parser.ParseException;

    String callOpenAPI() throws IOException;

    JSONArray getJsonArray(String response);

    void parseAndSave(JSONArray jsonArray) throws ParseException, IOException, org.json.simple.parser.ParseException;

}
