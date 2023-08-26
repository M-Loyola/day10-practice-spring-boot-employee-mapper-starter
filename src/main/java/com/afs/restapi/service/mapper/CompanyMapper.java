package com.afs.restapi.service.mapper;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.service.dto.CompanyRequest;
import com.afs.restapi.service.dto.CompanyResponse;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class CompanyMapper {
    public static Company toEntity(Company company, CompanyRequest companyRequest) {
        if (company == null) {
            company = new Company();
        }
        BeanUtils.copyProperties(companyRequest, company);
        return company;
    }

    public static CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);
        companyResponse.setEmployeesCount(getEmployeesCount(company));
        return companyResponse;
    }

    private static int getEmployeesCount(Company company) {
        List<Employee> employees = company.getEmployees();
        return (employees == null) ? 0 : employees.size();
    }
}
