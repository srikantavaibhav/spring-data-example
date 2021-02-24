package com.example.springdataexample.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Department {
    @Id
    @GenericGenerator(name = "employee_id_seq", strategy = "increment")
    @GeneratedValue(generator = "employee_id_seq", strategy = GenerationType.AUTO)
    private Long departmentId;

    private String departmentName;

    @JoinColumn(referencedColumnName = "departmentId", name = "department_department_id")
    @OneToMany
    List<Employee> employeeList;
}
