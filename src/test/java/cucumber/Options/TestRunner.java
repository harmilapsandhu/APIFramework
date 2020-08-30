package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",
                 glue = "stepDefinitions",
                 plugin = "json:target/jsonReports/cucumber-report.json") //Given input directory for Reports
               //tags = "@DeletePlace")  //Use tags to only run selected tests

//A single scenario can have multiple tags in feature file
//To run tag tests from Maven: mvn test -Dcucumber.options="--tags @DeletePlace"
//D stands for the parameter that you want to run

//Reporting
//copy and the paste the plugin from this URL in pom.xml: https://github.com/damianszczepanik/maven-cucumber-reporting
//can remove these fields from plugin:  <classificationDirectory> and <classificationFiles>
//Enter version number in (check version above)
//Run with: mvn verify

public class TestRunner {

}
