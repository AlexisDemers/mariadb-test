package ade.practice.mariadb.database;

import ade.practice.mariadb.entities.Employee;

import java.util.List;

public class EmployeeDataAccess extends BaseDataAccess<Employee> {

    public EmployeeDataAccess() {
        super(Employee.class);
    }
}
