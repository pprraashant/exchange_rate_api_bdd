package com.test.steps;

import com.test.framework.PostmanExecute;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RateSteps {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    PostmanExecute execute;
    ValidatableResponse response;

    @When("^The API is available$")
    public void ratesApiIsAvailable()
    {
        logger.info("executing rate api to check if it is available");
        execute = new PostmanExecute();
        Map<String,Object> values= new HashMap<>();
        values.put("server","api.ratesapi.io");
        values.put("param","latest");
        response=execute.run("latestRate",values);
    }

    @Then("^assert the success status of the response$")
    public void assertStatus()
    {
        response.statusCode(200).assertThat();
    }

    @Then("^assert the response$")
    public void assertResponse()
    {
        response.body("base",equalTo("EUR"),new Object[0]).assertThat();
    }
}
