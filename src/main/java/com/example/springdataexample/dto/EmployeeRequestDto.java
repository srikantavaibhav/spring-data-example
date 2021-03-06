package com.example.springdataexample.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmployeeRequestDto {
    private Long id;
    private String name;
    private DepartmentRequestDto department;
    private String code;
}
