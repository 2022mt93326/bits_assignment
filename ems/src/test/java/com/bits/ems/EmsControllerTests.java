package com.bits.ems;

import com.bits.ems.controller.EmployeeController;
import com.bits.ems.dto.Employee;
import com.bits.ems.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

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

	@Test
	public void shouldReturnEmployeeWhenFound() {
		List<Employee> employees = controller.getEmployees();
		String searchId = employees.get(0).getId().toString();
		Employee employee = controller.getEmployeeById(searchId);
		Assert.assertNotNull(employee);
		Assert.assertEquals(searchId, employee.getId().toString());
	}

	@Test(expected = NotFoundException.class)
	public void shouldThrowWhenEmployeeNotFound() {
		String searchId = UUID.randomUUID().toString();
		controller.getEmployeeById(searchId);
	}
}
