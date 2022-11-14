package com.skypro.employee.model;

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
    public String toString() {
        return this.firstName + " " + this.lastName + "" + this.department + " " + this.salary;
    }

    public Integer getId() {

        return this.id;
    }
}











