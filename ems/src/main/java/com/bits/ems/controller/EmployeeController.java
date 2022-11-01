package com.bits.ems.controller;

import com.bits.ems.dto.Employee;
import com.bits.ems.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final List<Employee> employeeList = new ArrayList<>();

    public EmployeeController() {
        employeeList.add(new Employee(UUID.randomUUID(), "Purbita Jana", "9088456700"));
        employeeList.add(new Employee(UUID.randomUUID(), "Priya Prakash", "9900989099"));
        employeeList.add(new Employee(UUID.randomUUID(), "Suneel Patil", "7899870098"));
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeList;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        Optional<Employee> employee = employeeList.stream().filter(emp -> emp.getId().equals(UUID.fromString(id))).findAny();
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new NotFoundException("Employee not found");
        }
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employeeList.add(employee);
        return employee;
    }
}
