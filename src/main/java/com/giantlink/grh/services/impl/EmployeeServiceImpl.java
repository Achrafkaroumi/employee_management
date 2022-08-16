package com.giantlink.grh.services.impl;

import com.giantlink.grh.entities.Employee;
import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.mappers.EmployeeMapper;
import com.giantlink.grh.models.Requests.EmployeeRequest;
import com.giantlink.grh.models.Responses.EmployeeResponse;
import com.giantlink.grh.repositories.EmployeeRepository;
import com.giantlink.grh.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeResponse> get() throws NotFoundException {
        List<Employee> employeeList = employeeRepository.findAll();
        if(employeeList.isEmpty()){
            throw new NotFoundException("No employee found !");
        }
        return EmployeeMapper.INSTANCE.employeeListToResponse(employeeList);
    }

    @Override
    public EmployeeResponse get(Integer id) throws NotFoundException{
        Optional<Employee> findEmployeeId = employeeRepository.findById(id);
        if(!findEmployeeId.isPresent()){
            throw new NotFoundException("Employee not found");
        }
        return EmployeeMapper.INSTANCE.employeeToResponse(findEmployeeId.get());
    }

    @Override
    public EmployeeResponse add(EmployeeRequest employeeRequest) throws AlreadyExists {
        if(employeeRepository.findByFirstName(employeeRequest.getFirstName())!=null && employeeRepository.findByLastName(employeeRequest.getLastName())!=null) {
            throw new AlreadyExists("Employee with first name : "+employeeRequest.getFirstName()+" and last name : "+employeeRequest.getLastName()+" already exists");
        }
        Employee employee = EmployeeMapper.INSTANCE.requestToEmployee(employeeRequest);
        return EmployeeMapper.INSTANCE.employeeToResponse(employeeRepository.save(employee));
    }

    @Override
    public EmployeeResponse update(Integer id, EmployeeRequest employeeRequest) throws  NotFoundException {
        Employee employee = EmployeeMapper.INSTANCE.requestToEmployee(employeeRequest);
        Optional<Employee> findEmployeeId = employeeRepository.findById(id);
        if(!findEmployeeId.isPresent()){
            throw new NotFoundException("Employee not found");
        }
        if(findEmployeeId.isPresent()){
                employee.setId(id);
        }
        return EmployeeMapper.INSTANCE.employeeToResponse(employeeRepository.save(employee));
    }

    @Override
    public void delete(Integer id) throws NotFoundException{
        if(!employeeRepository.findById(id).isPresent()){
            throw new NotFoundException ("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
}
