package feature.account.mongo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.List;

import javax.annotation.Resource;

import com.cninfo.shtb.member.domain.AccountAggregate;
import com.cninfo.shtb.mongo.entity.Account;
import com.cninfo.shtb.mongo.service.IAccountService;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;


public class AccountScenario {
	@Resource(name = "shtb_AccountService")
	protected IAccountService accountService;

	@Before(value = { "@AccountFeature" })
	public void init() {
		this.accountService.deleteAll();
	}

	@After(value = { "@AccountFeature" })
	public void after() {
		System.out.println("end ....");
	}

	@Given("^the account management system is initialized with the following data$")
	public void the_account_management_system_is_initialized_with_the_following_data(
			final List<Account> accounts) throws Throwable {
		this.accountService.saveList(accounts);
	}

	@When("^account id (\\d+) transfer (\\d+) to account id (\\d+)$")
	public void account_id_transfer_to_accountt_id(int fromId, int money,
			int toId) throws Throwable {
		this.accountService.transfer(fromId, toId, money);
	}
	

	@Then("^account id (\\d+)'s money is (\\d+), account id (\\d+)'s money is (\\d+)$")
	public void account_id_s_money_is_account_id_s_money_is(int fromId,
			int fromMoney, int toId, int toMoney) throws Throwable {
		AccountAggregate account = this.accountService.getAccount(fromId);
		assertThat(account.getMoney(), equalTo(fromMoney));
		account = this.accountService.getAccount(toId);
		assertThat(account.getMoney(), equalTo(toMoney));
	}

}
