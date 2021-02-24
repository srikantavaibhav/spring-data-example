package com.example.springdataexample.dto;

import com.example.springdataexample.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto
{
    private Long id;
    private String name;
    private DepartmentResponseDto department;

    public void setDepartmentFromEntity(Department departmentFromEntity){
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        departmentResponseDto.setDepartmentId(departmentFromEntity.getDepartmentId());
        departmentResponseDto.setDepartmentName(departmentFromEntity.getDepartmentName());
        this.department=departmentResponseDto;
    }
}
