package com.cninfo.shtb.salary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalaryManager {
	private Map<Integer, Employee> employees = new HashMap<Integer, Employee>();

	public SalaryManager(final List<Employee> employees) {
		for (Employee employee : employees) {
			this.employees.put(employee.getId(), employee);
		}
	}

	public void increaseSalary(final Integer id, final int increaseInPercent) {
		Employee nominee = employees.get(id);
		float oldSalary = nominee.getSalary();
		nominee.setSalary(oldSalary + oldSalary * increaseInPercent / 100);
	}

	public Employee getPayroll(final int id) {
		return employees.get(id);
	}
}
