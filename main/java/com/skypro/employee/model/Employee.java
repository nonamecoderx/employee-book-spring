package com.skypro.employee.model;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private int department;
    private int salary;
    private int id;
    static int counter = 1;

    public void displayId() {
        System.out.printf("Id: %d \n", id);
    }

    public Employee(String firstName, String lastName, int department, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
        id = counter++;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return this.firstName + this.lastName;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return this.department;
    }

    public int getSalary() {
        return this.salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && salary == employee.salary && id == employee.id && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, department, salary, id);
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + "" + this.department + " " + this.salary;
    }

    public Integer getId() {

        return this.id;
    }
}











