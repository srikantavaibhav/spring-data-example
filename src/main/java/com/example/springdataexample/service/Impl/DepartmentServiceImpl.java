package com.example.springdataexample.service.Impl;

import com.example.springdataexample.dto.DepartmentRequestDto;
import com.example.springdataexample.dto.DepartmentResponseDto;
import com.example.springdataexample.entity.Department;
import com.example.springdataexample.entity.Employee;
import com.example.springdataexample.repository.DepartmentRepository;
import com.example.springdataexample.repository.EmployeeRepository;
import com.example.springdataexample.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

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

    @Override
    @Transactional
    public DepartmentResponseDto updateDepartment(Long departmentId, DepartmentRequestDto departmentRequestDto){
        Department department = departmentRepository.findById(departmentId).get();
        List<Employee> employeeList = employeeRepository.findByDepartment_DepartmentId(departmentId);

        //update department
        department.setDepartmentName(departmentRequestDto.getDepartmentName());
        Department savedDepartment = departmentRepository.save(department);

        //append departmentCode to employee code
       // if(departmentId!=null)throw new RuntimeException("My Error");

        employeeList.forEach(employee -> {
            employee.setCode(departmentRequestDto.getDepartmentCode());
        });
        employeeRepository.saveAll(employeeList);


        DepartmentResponseDto responseDto = new DepartmentResponseDto();
        BeanUtils.copyProperties(savedDepartment,responseDto);

        return responseDto;
    }

    @Override
    public DepartmentResponseDto getMostExperiencedDepartment()
    {
        List<Employee> employeeList =(List<Employee>) employeeRepository.findAll();
        List<Long> departmentIdList = new ArrayList<>();
        for(Employee employee: employeeList)
        {
            departmentIdList.add(employee.getDepartment().getDepartmentId());
        }

        Map<Long,Integer> departmentAndExperience = new HashMap<>();
        for(Long departmentId: departmentIdList)
        {
            int departmentExperience = 0;
            for(Employee employee: employeeList)
            {
                if(departmentId==employee.getDepartment().getDepartmentId())
                {
                    departmentExperience+=employee.getYearsOfExperience();
                }
            }
            departmentAndExperience.put(departmentId, departmentExperience);
        }
        DepartmentResponseDto mostExperiencedDepartment = new DepartmentResponseDto();
        Employee employee=new Employee();

        int maxDepartmentExperience = 0;
        Long maxDepartmentExperienceID=null;
        for(Long departmartId: departmentAndExperience.keySet())
        {
            if(departmentAndExperience.get(departmartId)>maxDepartmentExperience)
            {
                maxDepartmentExperience=departmentAndExperience.get(departmartId);
                maxDepartmentExperienceID=departmartId;
            }
        }
        for(Employee empTemp: employeeList)
        {
            if(maxDepartmentExperienceID==empTemp.getDepartment().getDepartmentId()) {
                employee = empTemp;
                break;
            }
        }

        BeanUtils.copyProperties(employee.getDepartment(),mostExperiencedDepartment);
        return mostExperiencedDepartment;


    }

}
