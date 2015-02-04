package feature.account.json;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.lang.reflect.InvocationTargetException;

import com.cninfo.shtb.member.vo.AccountVO;
import com.google.gson.Gson;
import com.icloud.framework.webdriver.WebDriverFacade;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Then;
import cucumber.annotation.en_au.When;

public class AcccountJsonScenario {
	WebDriverFacade facade;

	@Before(value = { "@AccountJsonFeature" })
	public void init() {
		facade = WebDriverFacade.getInstance();
	}

	@After(value = { "@AccountJsonFeature" })
	public void after() throws IllegalAccessException,
			InvocationTargetException, InstantiationException {
		System.out.println("end ....");
		facade.closeBrowser();
	}

	@When("^start to load website$")
	public void start_to_load_website() throws Throwable {

	}

	@Then("^account id (\\d+)'s money is (\\d+)$")
	public void account_id_s_money_is(int id, int money) throws Throwable {
		facade.get("http://localhost:8080/icloud-train-demo/member/getAccountForUserId?userId="
				+ id);
		String pageSource = facade.getPageSource();

		AccountVO accountVO = new Gson().fromJson(pageSource, AccountVO.class);
		assertThat(accountVO.getMoney(), equalTo(money));
	}
}
