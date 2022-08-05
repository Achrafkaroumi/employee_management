package com.giantlink.grh.mappers;

import com.giantlink.grh.entities.Employee;
import com.giantlink.grh.models.Requests.EmployeeRequest;
import com.giantlink.grh.models.Responses.EmployeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee  requestToEmployee(EmployeeRequest employeeRequest);
    EmployeeResponse employeeToResponse(Employee employee);

    List<EmployeeResponse> employeeListToResponse(List<Employee> employee);
}
