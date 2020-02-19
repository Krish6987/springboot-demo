package com.Test.myWebApp_Test;

import org.springframework.stereotype.Repository;


 
@Repository
public class EmployeeDAO
{
     static Employees list = new Employees();
     
    static
    {
        list.getEmployeeList().add(new Employee(1, "Abhishek", "Kris", "abhi"));
        list.getEmployeeList().add(new Employee(2, "Sai", "Tej", "saitej"));
        list.getEmployeeList().add(new Employee(3, "Krishna", "kanta", "Krish"));
        list.getEmployeeList().add(new Employee(4, "Haary", "Richard", "haary"));
    }
     
    public Employees getAllEmployees()
    {
        return list;
    }
     
    public void addEmployee(Employee employee) {
        list.getEmployeeList().add(employee);
    }
}
