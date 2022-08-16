package com.giantlink.grh.services;

import com.giantlink.grh.entities.Employee;
import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.EmployeeRequest;
import com.giantlink.grh.models.Responses.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> get() throws NotFoundException;
    EmployeeResponse get(Integer id) throws NotFoundException;
    EmployeeResponse add(EmployeeRequest employeeRequest) throws AlreadyExists;
    EmployeeResponse update(Integer id, EmployeeRequest employeeRequest) throws NotFoundException;
    void delete(Integer id) throws NotFoundException;
}
