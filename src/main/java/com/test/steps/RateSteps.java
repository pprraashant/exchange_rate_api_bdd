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

    @When("The API is available \"([^\"]*)\"$")
    public void ratesApiIsAvailable(String param)
    {
        logger.info("executing rate api to check if it is available for: "+ param);
        execute = new PostmanExecute();
        Map<String,Object> values= new HashMap<>();
        values.put("server","api.ratesapi.io");
        values.put("param","latest");
        response=execute.run("latestRate",values);
    }

    @Then("^assert the success status of the response$")
    public void assertStatus()
    {
        logger.info("assert the response success status");
        response.statusCode(200).assertThat();
    }

    @Then("^assert the response$")
    public void assertResponse()
    {
        logger.info("assert the response body");
        response.body("base",notNullValue(),new Object[0]).assertThat();
    }

    @Then("^assert the response is expected$")
    public void correctResponseValidation()
    {
        logger.info("assert the response is expected");
        response.body("base",equalTo("EUR"),new Object[0]).assertThat();
    }

    @Then("assert the date \"([^\"]*)\"$")
    public void assertTheDate(String dateToValidate) {
        logger.info("assert the rate is available for date: "+ dateToValidate);
        response.body("date",equalTo(dateToValidate),new Object[0]).assertThat();
    }
}
