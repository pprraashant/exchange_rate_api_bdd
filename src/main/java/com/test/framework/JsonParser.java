package com.test.framework;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    public JsonParser() {
    }

    public String parse(String text, Map<String, Object> values) {
        Pattern pattern = Pattern.compile("\\{\\{[a-zA-Z_\\-0-9]*}}");

        String variable;
        Object varValue;
        for(Matcher matcher = pattern.matcher(text); matcher.find(); text = text.replace(variable, varValue.toString())) {
            variable = matcher.group();
            String varName = variable.replaceAll("\\{\\{|}}", "");
            varValue = values.get(varName);
            if (varValue == null) {
                throw new RuntimeException(String.format("variable %s not defined", varName));
            }
        }

        return text;
    }
}