package com.skypro.employee.service;


import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {
    private final Map<Integer, Employee> employees = new HashMap<>();
    EmployeeService employeeService = new EmployeeService();

    @BeforeEach
    public void setUp() {
        EmployeeRequest test1 = new EmployeeRequest();
        test1.setFirstName("алексей");
        test1.setLastName("павлов");
        test1.setDepartment(1);
        test1.setSalary(30_800);
        employeeService.addEmployee(test1);
        EmployeeRequest test2 = new EmployeeRequest();
        test2.setFirstName("Михаил");
        test2.setLastName("Иванов");
        test2.setDepartment(2);
        test2.setSalary(38_000);
        employeeService.addEmployee(test2);
        EmployeeRequest test3 = new EmployeeRequest();
        test3.setFirstName("Иван");
        test3.setLastName("Ануфриев");
        test3.setDepartment(1);
        test3.setSalary(65_500);
        employeeService.addEmployee(test3);
        EmployeeRequest test4 = new EmployeeRequest();
        test4.setFirstName("Петр");
        test4.setLastName("Кузьмин");
        test4.setDepartment(2);
        test4.setSalary(73_000);
        employeeService.addEmployee(test4);
    }

    @BeforeEach
    public void setUp2(){
        Employee test1 = new Employee("алексей", "павлов", 1, 30_800);
        employees.put(test1.getId(), test1);
        Employee test2 = new Employee("Михаил", "Иванов", 2, 38_000);
        employees.put(test2.getId(), test2);
        Employee test3 = new Employee("Иван", "Ануфриев", 1, 65_500);
        employees.put(test3.getId(), test3);
        Employee test4 = new Employee("Петр", "Кузьмин", 2, 73_000);
        employees.put(test4.getId(), test4);
    }

    @Test
    public void getAllEmployees() {
        assertEquals(employeeService.getAllEmployees().toString(), employees.values().toString());
    }

    @Test
    public void getSalarySum() {
        int sum = employees.values().stream().mapToInt(Employee::getSalary).sum();
        assertEquals(employeeService.getSalarySum(), sum);
    }

    @Test
    public void getSalaryMin() {
        int min = employees.values().stream().mapToInt(Employee::getSalary).min().orElseThrow();
        List<Employee> list = employeeService.getAllEmployees().stream().filter(e -> e.getSalary() == min).toList();
        assertEquals(employeeService.getSalaryMin(), list);
    }

    @Test
    public void getSalaryMax() {
        int max = employees.values().stream().mapToInt(Employee::getSalary).max().orElseThrow();
        List<Employee> list = employeeService.getAllEmployees().stream().filter(e -> e.getSalary() == max).toList();
        assertEquals(employeeService.getSalaryMax(), list);
    }

    @Test
    public void getHighSalary() {
        double middle = employees.values().stream().mapToInt(Employee::getSalary).average().orElseThrow();
        List<Employee> list = employeeService.getAllEmployees().stream().filter(e -> e.getSalary() > middle).toList();
        assertEquals(employeeService.highSalary(), list);
    }
}
