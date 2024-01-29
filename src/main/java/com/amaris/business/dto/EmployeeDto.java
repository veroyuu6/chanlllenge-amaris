package com.amaris.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private long id;
    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("employee_salary")
    private BigDecimal employeeSalary;
    @JsonProperty("employee_age")
    private Short employeeAge;



}
