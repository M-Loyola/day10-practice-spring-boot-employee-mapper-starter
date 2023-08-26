package com.afs.restapi.service.mapper;

import com.afs.restapi.entity.Company;
import com.afs.restapi.service.dto.CompanyRequest;
import com.afs.restapi.service.dto.CompanyResponse;
import org.springframework.beans.BeanUtils;

public class CompanyMapper {
    private CompanyMapper() {
    }

    public static Company toEntity(Company company, CompanyRequest companyRequest) {
        company = (company == null) ? new Company() : company;
        BeanUtils.copyProperties(companyRequest, company);
        return company;
    }

    public static CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);
        companyResponse.setEmployeesCount(company.getEmployees() != null ? company.getEmployees().size() : 0);
        return companyResponse;
    }
}
