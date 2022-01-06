package com.udemy.spring.springselenium.bdd;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    strict = true,
    features = "src/test/java/com/udemy/spring/springselenium/bdd/features/",
    glue = "com.udemy.spring.springselenium.bdd",
    tags = { "not @google" },
    plugin = {
        "json:target/cucumber.json",
        "pretty",
        "html:/Users/onur/Desktop/temp/cucumber-report/"
    }
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
