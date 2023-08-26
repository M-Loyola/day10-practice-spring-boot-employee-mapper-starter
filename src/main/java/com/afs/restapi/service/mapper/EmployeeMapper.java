package com.afs.restapi.service.mapper;

import com.afs.restapi.entity.Employee;
import com.afs.restapi.service.dto.EmployeeRequest;
import com.afs.restapi.service.dto.EmployeeResponse;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class EmployeeMapper {
    public EmployeeMapper() {
    }

    public static Employee toEntity(Employee employee, EmployeeRequest employeeRequest) {
        employee = initializeEmployeeIfNull(employee);
        BeanUtils.copyProperties(employeeRequest, employee);
        return employee;
    }

    private static Employee initializeEmployeeIfNull(Employee employee) {
        return Objects.isNull(employee) ? new Employee() : employee;
    }

    public static EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee, employeeResponse);
        return employeeResponse;
    }
}
