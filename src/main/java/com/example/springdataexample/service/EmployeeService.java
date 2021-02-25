package com.example.springdataexample.service;

import com.example.springdataexample.dto.EmployeeRequestDto;
import com.example.springdataexample.dto.EmployeeResponseDto;

import java.util.List;


public interface EmployeeService
{
    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto getEmployeeById(Long id);
    EmployeeResponseDto updateEmployeeById(Long id, EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto deleteEmployeeById(Long id);


    List<EmployeeResponseDto> getEmployeeByDepartment(Long departmentId);

    EmployeeResponseDto getMostExperiencedEmployeeAmongAllEmployee();

    EmployeeResponseDto getMostExperiencedEmployeeInADepartment(Long departmentId);
}
