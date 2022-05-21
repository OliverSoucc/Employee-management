package com.oliversouc.dal;

import com.oliversouc.be.Employee;

import java.util.List;

public interface FacadeDAO {
    List<Employee> getAllEmployeesDAO();
    List<Employee> filterResultDAO(String valueToFilter);
    Employee createEmployeeDAO(String name, double personalBonus, byte onLeave, String phoneNumber, double realSalary);
    void updateEmployeeDAO(Employee employee);
    void deleteEmployeeDAO(Employee employee);
    Employee getMovieDAO(int id);

}
