package junstest;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cinfo.shtb.cucumber.service.IAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/ctx-min.xml" })
public class Unsafda {
	@Resource(name = "de_accountService")
	protected IAccountService accountService;

	@Test
	public void afds() {
		accountService.save(null);
	}
}
