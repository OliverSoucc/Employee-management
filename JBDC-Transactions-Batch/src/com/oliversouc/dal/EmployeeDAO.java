package com.oliversouc.dal;


import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.oliversouc.be.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final DBConnector dbConnector;

    public EmployeeDAO(){
        dbConnector = new DBConnector();
    }

    public List<Employee> getAllEmployees(){
        List<Employee> allEmployees = new ArrayList<>();
        String query =  "SELECT * " +
                        "FROM employee " +
                        "JOIN SalaryGroup g ON SalaryGroupId = g.Id";
        try(Connection connection = dbConnector.getConnection()){
//            ("jdbc:sqlserver://10.176.111.31:1433/oliversouc_JDBCTest?useSSL=false",
//                                                                "CSe21B_26",
//                                                                "CSe21B_26")
//            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.addBatch();
//            preparedStatement.executeBatch();
            preparedStatement.execute();
//            connection.commit();
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()){
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                double personalBonus = rs.getDouble("PersonalBonus");
                byte onLeave = rs.getByte("OnLeave");
                String phoneNumber = rs.getString("PhoneNumber");
                double realSalary = rs.getDouble("Salary");
                Employee employee = new Employee(id, name, personalBonus, onLeave, phoneNumber, realSalary);
                System.out.println(employee);
                allEmployees.add(employee);
            }
        }
        catch (SQLServerException sqle){
            System.out.println("Error: " +  sqle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allEmployees;
    }

    //realSalary in this case is SalaryGroupId which is a foreign key to SalaryGroup where employee's salary is really stored
    public Employee createEmployee(String name, double salary, byte onLeave, String phoneNumber, double realSalary){
        Employee employee = null;
        int id = 0;
        String query = "INSERT INTO employee VALUES(?, ?, ?, ?, ?)";

        try(Connection connection = dbConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, salary);
            preparedStatement.setByte(3, onLeave);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setDouble(5, realSalary);
            int created = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()){
               id = resultSet.getInt(1);
            }
            if (created != 0){
                employee = new Employee(id, name, salary, onLeave, phoneNumber, realSalary);
            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public void deleteEmployee(Employee employee){
        String query = "DELETE FROM employee WHERE Id = ?";
        try(Connection connection = dbConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employee.getID());
            preparedStatement.executeUpdate();

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //when opens updateView the realSalary is preset in table but if you update
    //it won't update the realSalary to this employee
    //what needs to by done:
    //change this connector - updateEmployee
    //when you give realSalary to employee, set his SalaryGroupId to existing one or if this amount of realSalary is not existing create new SalaryGroup row with this amount
    public void updateEmployee(Employee employee){
        String query =  "UPDATE employee " +
                        "SET Name = ?, PersonalBonus = ?, OnLeave = ?, PhoneNumber = ? " +
                        "WHERE Id = ?";
        try (Connection connection = dbConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(5, employee.getID());
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setDouble(2, employee.getPersonalBonus());
            preparedStatement.setByte(3, employee.getOnLeave());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> filterResult(String valueToFilter){
        List<Employee> filteredEmployees = new ArrayList<>();
        String query =  "SELECT * " +
                        "FROM employee e " +
                        "JOIN SalaryGroup g ON e.SalaryGroupId = g.Id " +
                        "WHERE e.Id LIKE CONCAT(?, '%') " +
                        "OR Name LIKE CONCAT('%', ?, '%') " +
                        "OR OnLeave = ? " +
                        "OR PhoneNumber LIKE CONCAT('%', ?, '%') " +
                        "OR PersonalBonus LIKE CONCAT(?, '%') " +
                        "OR Salary LIKE CONCAT(?, '%') ";

            try (Connection connection = dbConnector.getConnection()){
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, valueToFilter);
                preparedStatement.setString(2, valueToFilter);
                preparedStatement.setString(3, valueToFilter);
                preparedStatement.setString(4, valueToFilter);
                preparedStatement.setString(5, valueToFilter);
                preparedStatement.setString(6, valueToFilter);
                preparedStatement.execute();

                ResultSet rs = preparedStatement.getResultSet();
                while (rs.next()){
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    double personalBonus = rs.getDouble("PersonalBonus");
                    byte onLeave = rs.getByte("OnLeave");
                    String phoneNumber = rs.getString("PhoneNumber");
                    double realSalary = rs.getDouble("Salary");

                    Employee employee = new Employee(id, name, personalBonus, onLeave, phoneNumber, realSalary);
                    filteredEmployees.add(employee);
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredEmployees;
    }

    public Employee getMovie(int id){
        Employee employee = null;
        String query =  "SELECT e.Name, e.PersonalBonus, e.OnLeave, e.PhoneNumber, g.Salary " +
                        "FROM employee e " +
                        "JOIN SalaryGroup g ON SalaryGroupId = g.Id " +
                        "WHERE e.Id = ?";

        try (Connection connection = dbConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()){
                String name = resultSet.getString("Name");
                double personalBonus = resultSet.getInt("PersonalBonus");
                byte onLeave = resultSet.getByte("OnLeave");
                String phoneNumber = resultSet.getString("PhoneNumber");
                double realSalary = resultSet.getInt("Salary");

                employee = new Employee(id, name, personalBonus, onLeave, phoneNumber, realSalary);

            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
