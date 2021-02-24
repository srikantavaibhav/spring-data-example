package com.example.springdataexample.service.Impl;


import com.example.springdataexample.dto.DepartmentResponseDto;
import com.example.springdataexample.dto.EmployeeRequestDto;
import com.example.springdataexample.dto.EmployeeResponseDto;
import com.example.springdataexample.entity.Department;
import com.example.springdataexample.entity.Employee;
import com.example.springdataexample.repository.DepartmentRepository;
import com.example.springdataexample.repository.EmployeeRepository;
import com.example.springdataexample.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService
{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto)
    {
        Employee employee = new Employee();

        //copy fields from dto to employee
        BeanUtils.copyProperties(employeeRequestDto,employee);

        //fetch Department from db
        Optional<Department> departmentOptional = departmentRepository.findById(employeeRequestDto.getDepartment().getDepartmentId());
        if(departmentOptional.isPresent()){
            employee.setDepartment(departmentOptional.get());
        }else {
            Department department = new Department();
            department.setDepartmentName(employeeRequestDto.getDepartment().getDepartmentName());
            employee.setDepartment(department);
        }

        //save employee to db
        Employee savedEmployee = employeeRepository.save(employee);

        //copy from employee to response dto
        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        BeanUtils.copyProperties(savedEmployee,responseDto);


        responseDto.setDepartmentFromEntity(savedEmployee.getDepartment());

        return responseDto;

    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id)
    {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent())
        {
            //copy from employee to response dto
            EmployeeResponseDto responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employeeOptional.get(),responseDto);
            responseDto.setDepartmentFromEntity(employeeOptional.get().getDepartment());

            return responseDto;
        }
        return null;
    }

    @Override
    public EmployeeResponseDto updateEmployeeById(Long id, EmployeeRequestDto employeeRequestDto)
    {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent())
        {
            //update employee
            Employee employeeFromDb = employeeOptional.get();
            employeeFromDb.setName(employeeRequestDto.getName());

            //fetch department from db
            Optional<Department> departmentOptional = departmentRepository.findById(employeeRequestDto.getDepartment().getDepartmentId());
            if(departmentOptional.isPresent()){
                employeeFromDb.setDepartment(departmentOptional.get());
            }else {
                Department department = new Department();
                department.setDepartmentName(employeeRequestDto.getDepartment().getDepartmentName());
                employeeFromDb.setDepartment(department);
            }



            //save employee to db
            Employee savedEmployee = employeeRepository.save(employeeFromDb);

            //copy from employee to response dto
            EmployeeResponseDto responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(savedEmployee,responseDto);

            responseDto.setDepartmentFromEntity(savedEmployee.getDepartment());

            return responseDto;
        }
        return null;
    }

    @Override
    public EmployeeResponseDto deleteEmployeeById(Long id)
    {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent())
        {
            //copy from employee to response dto
            EmployeeResponseDto responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employeeOptional.get(),responseDto);
            employeeRepository.deleteById(id);

            return responseDto;
        }
        return null;
    }

    @Override
    public List<EmployeeResponseDto> getEmployeeByDepartment(Long departmentId)
    {
        //Department department = departmentRepository.findById(departmentId).get();


        //List<Employee> employeeList = employeeRepository.findByDepartment(department);

        //List<Employee> employeeList = employeeRepository.findByDepartment_DepartmentId(departmentId);

        //List<Employee> employeeList = employeeRepository.getEmployeeListByDepartment(departmentId);

        //List<Employee> employeeList = employeeRepository.getEmployeeListByDepartmentIdParam(departmentId);

        List<Employee> employeeList = employeeRepository.getEmployeeListByNativeQuery(departmentId);

        List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();
        for(Employee employee:employeeList)
        {
            EmployeeResponseDto responseDto=new EmployeeResponseDto();
            BeanUtils.copyProperties(employee,responseDto);
            responseDto.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDtoList.add(responseDto);
        }
        return employeeResponseDtoList;
    }
}
