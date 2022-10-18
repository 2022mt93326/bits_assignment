package com.bits.ems;

import com.bits.ems.controller.EmployeeController;
import com.bits.ems.dto.Employee;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class EmsControllerTests {

	private final EmployeeController controller = new EmployeeController();
	@Test
	public void whenEmployeeDetailsAreProvidedCreatesNewEmployee() {
		Employee employee = Employee.builder()
				.name("Sneha Gupta")
				.contactNo("1234567890")
				.build();
		Employee newEmployee = controller.addEmployee(employee);
		Assert.assertNotNull(newEmployee);
		Assert.assertNotNull(newEmployee.getId());
		Assert.assertEquals(employee.getName(),newEmployee.getName());
		Assert.assertEquals(employee.getContactNo(),newEmployee.getContactNo());
	}

	@Test
	public void shouldReturnListOfAllEmployees() {
		List<Employee> employees = controller.getEmployees();
		Assert.assertNotNull(employees);
		Assert.assertTrue(employees.size() > 0);
	}

}
