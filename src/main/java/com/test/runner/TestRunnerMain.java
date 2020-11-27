package com.test.runner;

import com.test.framework.PostmanExecute;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

public class TestRunnerMain {

    public static void main(String [] args)
    {
        System.out.println("Started Executing new Test");
        PostmanExecute   execute = new PostmanExecute();
        Map<String,Object> values= new HashMap<String,Object>();
        ValidatableResponse response=execute.run("latestRate",values);
        System.out.println(response.assertThat().statusCode(200));
    }
}
