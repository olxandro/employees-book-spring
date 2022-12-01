package com.skypro.employee.service;


import com.skypro.employee.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    private List<Employee> list;

    @BeforeEach
    public void setUp() {
        Employee test1 = new Employee("TestName1", "TestFamily1", 1, 1000);
        Employee test2 = new Employee("TestName2", "TestFamily2", 2, 2000);
        Employee test3 = new Employee("TestName3", "TestFamily3", 2, 3000);
        list = List.of(test1, test2, test3);
        Mockito.when(employeeService.getAllEmployees()).thenReturn(list);
    }

    @Test
    public void getAllEmployeeDepartment() {
        Assertions.assertEquals(departmentService.getAllEmployeesInDepartment(2),
                list.stream().filter(e -> e.getDepartment() == 2).toList());
    }

    @Test
    public void getSumDepartment() {
        Assertions.assertEquals(departmentService.getSumSalaryInDepartment(2), 5000);
    }

    @Test
    public void getMaxSalaryDepartment() {
        Assertions.assertEquals(departmentService.getMaxSalaryInDepartment(2), 3000);
    }

    @Test
    public void getMinSalaryDepartment() {
        Assertions.assertEquals(departmentService.getMinSalaryInDepartment(2), 2000);
    }

    @Test
    public void allDepartments() {
        Map<Integer, List<Employee>> collect = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            int finalI = i;
            collect.put(i, list.stream().filter(e -> e.getDepartment() == finalI).toList());
        }
        Mockito.when(employeeService.getAllEmployees()).thenReturn(list);
        Assertions.assertEquals(departmentService.employeesGroupedByDepartment(), collect);
    }
}
