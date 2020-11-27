package com.test.framework;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

public class PostmanFileRunner {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private JsonParser parser;

    public PostmanFileRunner() {
        RestAssured.useRelaxedHTTPSValidation();
        this.parser = new JsonParser();
    }

    public ValidatableResponse run(PostmanFileReader reader, Map<String, Object> parameters) {
        JsonNode action = reader.read();
        return this.makeHttpRequest(parameters, action, this.parser.parse(action.get("request").get("body").get("raw").asText(), parameters));
    }

    private ValidatableResponse makeHttpRequest(Map<String, Object> parameters, JsonNode action, String body) {
        String method = action.get("request").get("method").asText();
        String url = this.parser.parse(action.get("request").get("url").get("raw").asText(), parameters);
        Map<String, String> headers = this.getHeaders(action.get("request").get("header"), parameters);
        Map<String, String> params = url.contains("?") ? this.getParams(url.substring(url.indexOf("?") + 1)) : Collections.emptyMap();
        this.logger.info("Sending request: " + url + "\nBody: " + body);
        return RestAssured.given().headers(headers).when().body(body).queryParams(params).baseUri(url).request(method).then();
    }

    private Map<String, String> getHeaders(JsonNode jsonNode, Map<String, Object> params) {
        Map<String, String> toReturn = new HashMap();
        Iterator elements = jsonNode.elements();

        while(elements.hasNext()) {
            JsonNode i = (JsonNode)elements.next();
            toReturn.put(i.get("key").asText(), this.parser.parse(i.get("value").asText(), params));
        }

        return toReturn;
    }

    private Map<String, String> getParams(String params) {
        String[] paramsArr = params.split("&");
        Map<String, String> toReturn = new HashMap();
        String[] var4 = paramsArr;
        int var5 = paramsArr.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String param = var4[var6];
            int index = param.indexOf("=");
            toReturn.put(param.substring(0, index), param.substring(index + 1));
        }

        return toReturn;
    }
}
