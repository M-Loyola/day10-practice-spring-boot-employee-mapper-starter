package com.afs.restapi.service;

import com.afs.restapi.entity.Employee;
import com.afs.restapi.exception.EmployeeNotFoundException;
import com.afs.restapi.repository.EmployeeRepository;
import com.afs.restapi.service.dto.EmployeeRequest;
import com.afs.restapi.service.dto.EmployeeResponse;
import com.afs.restapi.service.mapper.EmployeeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(EmployeeNotFoundException::new);
        return EmployeeMapper.toResponse(employee);
    }

    public EmployeeResponse update(Long id, EmployeeRequest employeeRequest) {
        Employee toBeUpdatedEmployee = employeeRepository.findById(id)
                .orElseThrow(EmployeeNotFoundException::new);
        checkIfSalaryAndAgeIsNull(employeeRequest, toBeUpdatedEmployee);
        EmployeeMapper.toEntity(toBeUpdatedEmployee, employeeRequest);
        return EmployeeMapper.toResponse(employeeRepository.save(toBeUpdatedEmployee));
    }

    private static void checkIfSalaryAndAgeIsNull(EmployeeRequest employeeRequest, Employee toBeUpdatedEmployee) {
        if (Objects.isNull(employeeRequest.getSalary())) {
            toBeUpdatedEmployee.setSalary(employeeRequest.getSalary());
        }
        if (Objects.isNull(employeeRequest.getAge())) {
            toBeUpdatedEmployee.setAge(employeeRequest.getAge());
        }
    }

    public List<EmployeeResponse> findAllByGender(String gender) {
        return employeeRepository.findAllByGender(gender).stream()
                .map(EmployeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse create(EmployeeRequest employeeRequest) {
        Employee employee = EmployeeMapper.toEntity(null, employeeRequest);
        return EmployeeMapper.toResponse(employeeRepository.save(employee));
    }

    public List<EmployeeResponse> findByPage(Integer pageNumber, Integer pageSize) {
        Page<Employee> employeesInThePage = employeeRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));
        return employeesInThePage.stream()
                .map(EmployeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
