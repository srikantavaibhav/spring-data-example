package com.example.springdataexample.service;

import com.example.springdataexample.dto.DepartmentRequestDto;
import com.example.springdataexample.dto.DepartmentResponseDto;
import com.example.springdataexample.dto.EmployeeResponseDto;
import com.example.springdataexample.entity.Department;

public interface DepartmentService {
    DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto);

    Department getDepartmentById(Long departmentId);

    DepartmentResponseDto updateDepartment(Long departmentId, DepartmentRequestDto departmentRequestDto);

    DepartmentResponseDto getMostExperiencedDepartment();
}
