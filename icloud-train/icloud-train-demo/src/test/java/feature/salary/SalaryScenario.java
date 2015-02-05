package feature.salary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.List;

import javax.annotation.Resource;

import com.cninfo.shtb.mongo.entity.Member;
import com.cninfo.shtb.mongo.service.IMemberService;
import com.cninfo.shtb.salary.Employee;
import com.cninfo.shtb.salary.SalaryManager;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en_au.When;

public class SalaryScenario {
	@Resource(name = "shtb_MemberService")
	protected IMemberService memberService;

	SalaryManager salaryManager = null;

	@Before(value = { "@manageFeature" })
	public void init() {
		System.out.println("init ....");
	}

	@After(value = { "@manageFeature" })
	public void after() {
		System.out.println("end ....");
	}

	@Given("^the employee management system is initialized with the following data$")
	public void the_employee_management_system_is_initialized_with_the_following_data(
			final List<Employee> employees) throws Throwable {
		salaryManager = new SalaryManager(employees);
	}

	@When("^the boss increases the salary for the employee with id (\\d+) by (\\d+)%$")
	public void the_boss_increases_the_salary_for_the_employee_with_id_by(
			final int id, final int increaseInPercent) throws Throwable {
		salaryManager.increaseSalary(id, increaseInPercent);
	}

	@Then("^the payroll for the employee with id (\\d+) should display a salary of (\\d+)$")
	public void the_payroll_for_the_employee_with_id_should_display_a_salary_of(
			int id, float salary) throws Throwable {
		Employee nominee = salaryManager.getPayroll(id);
		assertThat(nominee.getSalary(), equalTo(salary));
	}

}
