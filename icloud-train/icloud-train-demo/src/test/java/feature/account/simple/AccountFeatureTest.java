package feature.account.simple;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.List;

import javax.annotation.Resource;

import com.cinfo.shtb.cucumber.domain.AccountAggregate;
import com.cinfo.shtb.cucumber.service.IAccountService;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

//@Resource(name = "de_accountService")
//protected IAccountService accountService = new AccountService();

public class AccountFeatureTest {
	@Resource(name = "accountService")
	protected IAccountService accountService;

	@Given("^初始化帐户系统，以下是初始化数据$")
	public void 初始化帐户系统_以下是初始化数据(List<AccountAggregate> users) throws Throwable {
		for (AccountAggregate account : users) {
			accountService.save(account);
		}
	}

	@When("^userId 为 (\\d+)的帐户, 给 userId 为 (\\d+)的帐户转(\\d+)元$")
	public void userId_为_的帐户_给_userId_为_的帐户转_元(int fromUserId, int toUserId,
			int money) throws Throwable {
		accountService.transfer(fromUserId, toUserId, money);
	}

	// @Then("^userId 为 (\\d+)的帐户的余额应该是(\\d+)元，userId 为 (\\d+)的余额应该是(\\d+)元$")
	// public void userId_为_的帐户的余额应该是_元_userId_为_的余额应该是_元(int fromUserId,
	// int fromUserMoney, int toUserId, int toUserMoney) throws Throwable {
	//
	// }
	@Then("^userId 为 (\\d+)的帐户的余额应该是(\\d+)元，userId 为(\\d+)的余额应该是(\\d+)元$")
	public void userId_为_的帐户的余额应该是_元_userId_为_的余额应该是_元(int fromUserId,
			int fromUserMoney, int toUserId, int toUserMoney) throws Throwable {
		AccountAggregate fromAccount = accountService
				.getAccountAggregate(fromUserId);
		AccountAggregate toAccount = accountService
				.getAccountAggregate(toUserId);
		assertThat(fromAccount.getMoney(), equalTo(fromUserMoney));
		assertThat(toAccount.getMoney(), equalTo(toUserMoney));
	}
}
