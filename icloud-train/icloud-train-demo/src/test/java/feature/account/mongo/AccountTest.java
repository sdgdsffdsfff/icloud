package feature.account.mongo;

import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@Cucumber.Options(format = {"html:target/cucumber", "json:target/cucumber.json","junit:target/cucumber-junit-report.xml"},features = { "classpath:feature/account/mongo/Account_management.feature"} )
public class AccountTest {

}
