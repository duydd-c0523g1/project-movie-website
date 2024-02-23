package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Category;
import com.example.movie_ticket.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Page<Employee> findAllEmployee(Pageable pageable);
    List<Employee> findAllEmployee();
    void createEmployee(Employee employee);

    Optional<Employee> findEmployeeById(Long id);
    void updateEmployee(Employee employee);
    void deleteEmployee(Long id);
    List<Employee> getEmployeeAccountId(Long AccountId);
    Page<Employee> searchByName(String nameEmployee,Pageable pageable);
}
