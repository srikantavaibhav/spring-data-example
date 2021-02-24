package com.example.springdataexample.controller;

import com.example.springdataexample.dto.EmployeeRequestDto;
import com.example.springdataexample.dto.EmployeeResponseDto;
import com.example.springdataexample.entity.Employee;
import com.example.springdataexample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    //POST - /employee
    @PostMapping
    public EmployeeResponseDto createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto)
    {
        return employeeService.createEmployee(employeeRequestDto);

    }

    //GET - /employee{id}
    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeById(@PathVariable Long id)
    {
        return employeeService.getEmployeeById(id);
    }


    //PUT - /employee{id}
    @PutMapping("/{id}")
    public EmployeeResponseDto updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeRequestDto employeeRequestDto)
    {
        return employeeService.updateEmployeeById(id, employeeRequestDto);
    }


    //DELETE - /employee{id}
    @DeleteMapping("/{id}")
    public EmployeeResponseDto deleteEmployee(@PathVariable("id") Long id, @RequestBody EmployeeResponseDto employeeResponseDto)
    {
        return employeeService.deleteEmployeeById(id);
    }

    //GET - /employee/department/{id}
    @GetMapping("/department/{id}")
    public List<EmployeeResponseDto> getEmployeeListByDepartment(@PathVariable("id") Long id)
    {
        return employeeService.getEmployeeByDepartment(id);
    }
}
