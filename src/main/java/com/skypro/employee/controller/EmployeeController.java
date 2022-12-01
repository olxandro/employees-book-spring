package com.skypro.employee.controller;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * HTTP МЕТОДЫ
 * GET - получение ресурса или набора ресурсов
 * POST - создание ресурса
 * PUT - мобификация ресурса
 * PATCH - частичная модификация ресурса
 * DELETE - удаление ресурса
 */

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.addEmployee(employeeRequest);
    }
    @GetMapping("/employees/salary/sum")
    public int sum() {
        return employeeService.getSalarySum();
    }
    @GetMapping("/employees/salary/min")
    public List<Employee> min() {
        return employeeService.getSalaryMin();
    }

    @GetMapping("/employees/salary/max")
    public List<Employee> max() {
        return employeeService.getSalaryMax();
    }
    @GetMapping("/employees/high-salary")
    public List<Employee> highSalary() {
        return employeeService.highSalary();
    }
}
