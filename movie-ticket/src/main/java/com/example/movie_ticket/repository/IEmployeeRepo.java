package com.example.movie_ticket.repository;


import com.example.movie_ticket.model.Category;
import com.example.movie_ticket.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmployeeRepo extends JpaRepository<Employee, Long> {
    @Query(nativeQuery = true,value = "select * from Employee where account_id =:param")
    List<Employee> getEmployeeAccountId(@Param("param") Long idAccount);

    Page<Employee> findByIdAndFullNameContaining(Long idEmployee, String nameEmployee, Pageable pageable);
    Page<Employee> findByFullNameContaining(String nameEmployee, Pageable pageable);
}
