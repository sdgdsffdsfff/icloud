package junit.simple;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import com.cinfo.shtb.cucumber.domain.AccountAggregate;
//import com.cinfo.shtb.cucumber.service.IAccountService;
import com.cninfo.shtb.mongo.service.IAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/ctx-min.xml" })
public class AccountTest {
//	@Resource(name = "accountService")
//	protected IAccountService accountService;
	@Resource(name = "shtb_AccountService")
	protected IAccountService accountService;

	@Test
	public void findAll() {
		// accountService.save(null);
//		AccountAggregate accountAggregate = accountService
//				.getAccountAggregate(1);
		com.cninfo.shtb.member.domain.AccountAggregate account = accountService.getAccount(1);
	}
}
