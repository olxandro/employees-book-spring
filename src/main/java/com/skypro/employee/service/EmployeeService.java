package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null ||
                employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Employee name should be set");
        }
        if (!StringUtils.isAlpha(employeeRequest.getFirstName()) || !StringUtils.isAlpha(employeeRequest.getLastName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Employee employee = new Employee(
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public List<Employee> getSalaryMin() {
//        int minSalary = getSalarySum();
//        Employee minEmp = null;
//        for (Map.Entry<Integer, Employee> entry :
//                employees.entrySet()) {
//            if (entry.getValue().getSalary() < minSalary) {
//                minSalary = entry.getValue().getSalary();
//                minEmp = entry.getValue();
//            }
//        }
//        return minEmp;
        return employees.values().stream()
                .min(Comparator.comparing(Employee::getSalary))
                .stream().toList();

    }

    public List<Employee> getSalaryMax() {
//        int max = 0;
//        Employee maxEmp = null;
//        for (Map.Entry<Integer, Employee> entry :
//                employees.entrySet()) {
//            if (entry.getValue().getSalary() > max) {
//                max = entry.getValue().getSalary();
//                maxEmp = entry.getValue();
//            }
//        }
//        return maxEmp;
        return employees.values().stream()
                .max(Comparator.comparing(Employee::getSalary))
                .stream().toList();
    }

    public List<Employee> highSalary() {
        List<Employee> employeeList = new ArrayList<>();
        double medium = mediumSalary();
        for (Map.Entry<Integer, Employee> entry :
                employees.entrySet()) {
            if (entry.getValue().getSalary() > medium) {
                employeeList.add(entry.getValue());
            }
        }
        return employeeList;
    }

    public double mediumSalary() {
//        int sum = 0;
//        for (Map.Entry<Integer, Employee> entry:
//                employees.entrySet()) {
//            sum += entry.getValue().getSalary();
//        }
//        return sum / employees.size();
        return employees.values().stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
    }
}
