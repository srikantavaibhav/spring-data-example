package com.example.springdataexample.repository;

import com.example.springdataexample.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long>
{
}
