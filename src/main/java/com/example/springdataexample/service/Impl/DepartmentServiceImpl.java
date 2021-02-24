package com.example.springdataexample.service.Impl;

import com.example.springdataexample.dto.DepartmentRequestDto;
import com.example.springdataexample.dto.DepartmentResponseDto;
import com.example.springdataexample.entity.Department;
import com.example.springdataexample.repository.DepartmentRepository;
import com.example.springdataexample.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto)
    {
        Department department = new Department();

        //copy fields from dto to department
        BeanUtils.copyProperties(departmentRequestDto,department);

        //save department to db
        Department savedDepartment = departmentRepository.save(department);

        //copy from department to response dto
        DepartmentResponseDto responseDto = new DepartmentResponseDto();
        BeanUtils.copyProperties(savedDepartment,responseDto);

        return responseDto;
    }

    @Override
    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).get();
    }

}
