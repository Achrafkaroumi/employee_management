package com.giantlink.grh.services;

import com.giantlink.grh.entities.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> get();
    Employee get(Integer id);
    Employee add(Employee employee);
    void delete(Integer id);
}
