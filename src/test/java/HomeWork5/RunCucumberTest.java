package HomeWork5;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber-reports/CucumberTests.json",
                "junit:target/cucumber-reports/CucumberTests.xml",
                "html:target/cucumber-reports/index.html"},
        glue = {"src.test.java.HomeWork5.steps"},
        features = "src/test/java/resources/features",
        monochrome = true)
public class RunCucumberTest {

}
