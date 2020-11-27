package com.test.framework;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class PostmanResourceFileReader implements PostmanFileReader {
    private final String resourcePath;
    private ClassLoader classLoader;
    private ObjectMapper mapper = new ObjectMapper();

    public PostmanResourceFileReader(ClassLoader classLoader, String resourcePath) {
        this.classLoader = classLoader;
        this.resourcePath = resourcePath;
    }

    public JsonNode read() {
        InputStream stream = this.classLoader.getResourceAsStream(this.resourcePath);

        try {
            return this.mapper.readTree(stream);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }
}
