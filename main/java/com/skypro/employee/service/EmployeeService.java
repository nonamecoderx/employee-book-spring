//создали сервис, служит для работы с базой сотрудников.
package com.skypro.employee.service;

import com.skypro.employee.model.Employee;
import com.skypro.employee.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    public EmployeeService() {
    }

    private final Map<Integer, Employee> employees = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return this.employees.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Employee firstname or lastname should be set");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getDepartment(),
                employeeRequest.getSalary());
        this.employees.put(employee.getId(), employee);
        return employee;
    }

    public int getSalarySum() {
        return employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee getSalaryMax() {
        return employees.values()
                .stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(RuntimeException::new);
    }

    public Employee getSalaryMin() {
        return employees.values()
                .stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(RuntimeException::new);
    }

    public Set<Employee> getHigherAverage() {
        if (employees.size() == 0){
            throw new RuntimeException("Добавьте хоть одного сотрудника");
        }
        int average = getSalarySum()/employees.size();
        return employees.values().stream().filter(s->s.getSalary()>average).collect(Collectors.toSet());
    }
    public Employee removeEmployee (int id){
        return employees.remove(id);
    }
}



