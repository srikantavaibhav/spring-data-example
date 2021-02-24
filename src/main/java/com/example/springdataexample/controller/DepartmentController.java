package com.example.springdataexample.controller;

import com.example.springdataexample.dto.DepartmentRequestDto;
import com.example.springdataexample.dto.DepartmentResponseDto;
import com.example.springdataexample.entity.Department;
import com.example.springdataexample.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //POST - /department
    @PostMapping
    public DepartmentResponseDto createDepartment(@RequestBody DepartmentRequestDto departmentRequestDto)
    {
        return departmentService.createDepartment(departmentRequestDto);
    }

    @GetMapping("/{departmentId}")
    public Department getDepartment(@PathVariable("departmentId") Long departmentId){
        return departmentService.getDepartmentById(departmentId);
    }

    @PutMapping("/{departmentId}")
    public DepartmentResponseDto updateDepartment(@PathVariable("departmentId") Long departmentId, @RequestBody DepartmentRequestDto departmentRequestDto)
    {
        return departmentService.updateDepartment(departmentId, departmentRequestDto);
    }
}
