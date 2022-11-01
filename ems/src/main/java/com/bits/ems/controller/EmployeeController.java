package com.bits.ems.controller;

import com.bits.ems.dto.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final List<Employee> employeeList = new ArrayList<>();

    public EmployeeController() {
        employeeList.add(new Employee(UUID.randomUUID(), "Purbita Jana","9088456700"));
        employeeList.add(new Employee(UUID.randomUUID(), "Priya Prakash","9900989099"));
        employeeList.add(new Employee(UUID.randomUUID(), "Suneel Patil","7899870098"));
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeList;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employeeList.add(employee);
        return employee;
    }
}
