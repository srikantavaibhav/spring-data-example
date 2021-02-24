package com.example.springdataexample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequestDto {
    private Long departmentId;
    private String departmentName;
    private String departmentCode;
}
