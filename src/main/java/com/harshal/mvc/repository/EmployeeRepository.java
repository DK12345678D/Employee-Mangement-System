package com.harshal.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harshal.mvc.models.Employee;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  List<Employee> findAllByOrderBySalaryAsc();

  List<Employee> findAllByOrderBySalaryDesc();

  List<Employee> findByDepartment(String department); 
}
