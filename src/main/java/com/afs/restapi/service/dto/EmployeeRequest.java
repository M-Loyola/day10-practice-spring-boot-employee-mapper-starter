package com.afs.restapi.service.dto;

public class EmployeeRequest {
    private final String name;
    private final Integer age;
    private final String gender;
    private Integer salary;
    private Long companyId;

    public EmployeeRequest(String name, Integer age, String gender, Integer salary, Long companyId) {

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
