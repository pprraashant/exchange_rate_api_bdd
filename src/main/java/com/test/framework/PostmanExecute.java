package com.test.framework;

import io.restassured.response.ValidatableResponse;

import java.util.Map;
import java.util.logging.Logger;


public class PostmanExecute extends PostmanFileRunner {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public ValidatableResponse run(String stepName, Map<String, Object> parameters) {
            return super.run(readResourceFile(stepName), parameters);
    }
    private PostmanFileReader readResourceFile(String stepName) {
        return new PostmanResourceFileReader(this.getClass().getClassLoader(),"json/"+stepName+".json");

    }
}
