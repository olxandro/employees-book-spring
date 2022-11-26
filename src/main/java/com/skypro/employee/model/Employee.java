package com.skypro.employee.model;

import org.apache.commons.lang3.StringUtils;

public class Employee {
    private final int id;
    private static int counter;
    private final String firstName;
    private final String lastName;
    private final int department;
    private final int salary;

    public Employee(String firstName, String lastName, int department, int salary) {
        this.firstName = StringUtils.capitalize(firstName);
        this.lastName = StringUtils.capitalize(lastName);
        this.department = department;
        this.salary = salary;
        id = counter++;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Сотрудник: " +
                "id=" + id +
                ", имя - '" + firstName +
                ", фамилия - '" + lastName +
                ", отдел - '" + department +
                ", зарплата - " + salary;
    }
}
