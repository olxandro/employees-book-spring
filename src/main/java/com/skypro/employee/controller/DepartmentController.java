package com.skypro.employee.controller;


import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/{id}/employees")
    public Collection<Employee> department(@PathVariable("id") int departmentId) {
        return departmentService.getAllEmployeesInDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/sum")
    public String sumSalaryDepartment(@PathVariable("id") int departmentId) {
        return "Сумма зарплат всех сотрудников отдела № " + departmentId + " = " + departmentService.getSumSalaryInDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/max")
    public String maxSalaryDepartment(@PathVariable("id") int departmentId) {
        return "Максимальная зарплата отдела №" + departmentId + " = " + departmentService.getMaxSalaryInDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public String minSalaryDepartment(@PathVariable("id") int departmentId) {
        return "Минимальная зарплата отдела №" + departmentId + " = " + departmentService.getMinSalaryInDepartment(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> allDepartments() {
        return departmentService.employeesGroupedByDepartment();
    }
}
