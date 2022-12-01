package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    EmployeeService employeeService = new EmployeeService();

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getAllEmployeesInDepartment(int department) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public int getSumSalaryInDepartment(int id) {
        Collection<Employee> listEmployees = employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == id)
                .toList();
        return listEmployees.stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int getMaxSalaryInDepartment(int id) {
        Collection<Employee> listEmployees = employeeService.getAllEmployees().stream().filter(e -> e.getDepartment() == id).toList();
        return listEmployees.stream()
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(); //если Optional пустой кидает исключение NoSuchElementException
    }

    public int getMinSalaryInDepartment(int id) {
        Collection<Employee> listEmployees = employeeService.getAllEmployees().stream().filter(e -> e.getDepartment() == id).toList();
        return listEmployees.stream()
                .mapToInt(Employee::getSalary)
                .min()
                .orElseThrow(); //если Optional пустой кидает исключение NoSuchElementException

    }

    public Map<Integer, List<Employee>> employeesGroupedByDepartment() {
        int numberOfDepartments = employeeService.getAllEmployees().stream()
                .mapToInt(Employee::getDepartment)
                .max()
                .orElseThrow();
        Map<Integer, List<Employee>> GroupedDepartment = new HashMap<>();
        for (int i = 0; i <= numberOfDepartments; i++) {
            GroupedDepartment.put(i, (List<Employee>) getAllEmployeesInDepartment(i));
        }
        return GroupedDepartment;
    }
}
