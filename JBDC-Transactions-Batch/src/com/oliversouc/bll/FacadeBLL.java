package com.oliversouc.bll;

import com.oliversouc.be.Employee;

import java.util.List;

public interface FacadeBLL {
    List<Employee> getAllEmployeesManager();
    List<Employee> filterResultManager(String valuesToFilter);
    Employee createEmployeeManager(String name, double personalBonus, byte onLeave, String phoneNumber, double realSalary);
    void updateEmployeeManager(Employee employee);
    void deleteEmployeeManager(Employee employee);
    Employee getMovieManager(int id);

}
