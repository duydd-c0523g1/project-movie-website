package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.Employee;
import com.example.movie_ticket.repository.IEmployeeRepo;
import com.example.movie_ticket.service.ICategoryService;
import com.example.movie_ticket.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepo employeeRepo;

    @Override
    public Page<Employee> findAllEmployee(Pageable pageable) {
        return employeeRepo.findAll(pageable);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepo.findAll();
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeeAccountId(Long AccountId) {
        return employeeRepo.getEmployeeAccountId(AccountId);
    }

    @Override
    public Page<Employee> searchByName(String nameEmployee, Pageable pageable) {
            return employeeRepo.findByFullNameContaining(nameEmployee, pageable);
    }
}
