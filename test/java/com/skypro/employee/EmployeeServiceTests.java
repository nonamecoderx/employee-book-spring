package com.skypro.employee;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import com.skypro.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EmployeeServiceTests {
    private EmployeeService employeeService;


    @BeforeEach
    public void setUp() {
        this.employeeService = new EmployeeService();
        Stream.of(
                new EmployeeRequest("ta", "tb", 1, 6000),
                new EmployeeRequest("tc", "td", 2, 3000),
                new EmployeeRequest("te", "tf", 1, 7000),
                new EmployeeRequest("tg", "th", 1, 5000),
                new EmployeeRequest("tj", "tk", 1, 9000),
                new EmployeeRequest("tl", "tm", 2, 2000)
        ).forEach(employeeService::addEmployee);
    }

    //проверка добавления сотрудника
    @Test
    public void addEmployee() {
        EmployeeRequest request = new EmployeeRequest("Valid", "Valid", 3, 6000);
        Employee result = employeeService.addEmployee(request);
        assertEquals(request.getFirstName(), result.getFirstName());
        assertEquals(request.getLastName(), result.getLastName());
        assertEquals(request.getDepartment(), result.getDepartment());
        assertEquals(request.getSalary(), result.getSalary());
        org.assertj.core.api.Assertions.assertThat(employeeService.getAllEmployees()).contains(result);
    }

    //проверка количества добавленных сотрудников и совпадения имени первого сотрудника
    @Test
    public void listEmployees() {
        Collection<Employee> employees = employeeService.getAllEmployees();
        Assertions.assertThat(employees).hasSize(6);
        Assertions.assertThat(employees)
                .first()
                .extracting(Employee::getFirstName)
                .isEqualTo("ta");
    }

    //проверка суммы зарплат
    @Test
    public void sumOfSalaries() {
        int sum = employeeService.getSalarySum();
        Assertions.assertThat(sum).isEqualTo(32000);
    }

    //проверка на сотрудника с максимальной зарплатой
    @Test
    public void employeeWithMaxSalary() {
        Employee employee = employeeService.getSalaryMax();
        Assertions.assertThat(employee).isNotNull().extracting(Employee::getFirstName).isEqualTo("tj");
    }

    //проверка на сотрудника с минимальной зарплатой
    @Test
    public void employeeWithMinSalary() {
        Employee employee = employeeService.getSalaryMin();
        Assertions.assertThat(employee).isNotNull().extracting(Employee::getFirstName).isEqualTo("tl");
    }

    //проверка на сотрудника с зарплатой, выше средней(больше 5.33)
    @Test
    public void listHighThanAverageSalaryEmployees() {
        Collection<Employee> employees = employeeService.getHigherAverage();
        Assertions.assertThat(employees)
                .extracting(Employee::getFirstName)
                .isEqualTo(List.of("te","tj","ta"));
    }
    //проверка удаления сотрудника
    @Test
    public void removeEmployee(){
        employeeService.removeEmployee(1);
        Collection<Employee> employees = employeeService.getAllEmployees();
        Assertions.assertThat(employees).hasSize(5);
    }
}

