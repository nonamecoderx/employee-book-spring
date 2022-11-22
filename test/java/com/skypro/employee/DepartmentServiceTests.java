package com.skypro.employee;

import org.assertj.core.api.Assertions;
import com.skypro.employee.model.Employee;
import com.skypro.employee.service.DepartmentService;
import com.skypro.employee.service.EmployeeService;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTests {
    private final List<Employee> employees = List.of(
            new Employee("ta", "tb", 1, 6000),
            new Employee("tc", "td", 2, 3000),
            new Employee("te", "tf", 1, 7000),
            new Employee("tg", "th", 1, 5000),
            new Employee("tj", "tk", 1, 9000),
            new Employee("tl", "tm", 2, 2000)

    );
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);
    }

    @Test
    void getEmployeesByDepartment() {
        Collection<Employee> employeeList = this.departmentService.getDepartmentEmployee(1);
        assertThat(employeeList)
                .hasSize(4)
                .contains(employees.get(0),employees.get(2),employees.get(3),employees.get(4));
    }
    @Test
    void sumOfSalariesByDepartment(){
        int sum = this.departmentService.getSumOfDepartmentSalaries(1);
        assertThat(sum).isEqualTo(27000);
    }
    @Test
    void maxSalaryByDepartment(){
        int max = this.departmentService.getMaxOfDepartmentSalaries(2);
        assertThat(max).isEqualTo(3000);
    }
    @Test
    void minSalaryByDepartment(){
        int min = this.departmentService.getMinOfDepartmentSalaries(2);
        assertThat(min).isEqualTo(2000);
    }
    @Test
    void groupedByDepartment(){
        Map<Integer, List<Employee>> groupedByDepartment = this.departmentService
                .getEmployeesByDepartmentInGroups();
        assertThat(groupedByDepartment)
                .hasSize(2)
                .containsKey(1)
                .containsKey(2)
                .containsEntry(1,
                        List.of(employees.get(0),employees.get(2),employees.get(3),employees.get(4)))
                .containsEntry(2,
                        List.of(employees.get(1),employees.get(5)));
    }
}
