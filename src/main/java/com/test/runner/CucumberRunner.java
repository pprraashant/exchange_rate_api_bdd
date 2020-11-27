package com.test.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/features/latestRate",plugin  = {"pretty", "html:target/cucumber", "json:target/cucumber-results.json"}, glue="com.test.steps")
public class CucumberRunner {
}