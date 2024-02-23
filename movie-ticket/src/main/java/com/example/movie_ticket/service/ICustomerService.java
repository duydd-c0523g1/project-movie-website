package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomer();
    void deleteCustomerById(Long id);
    void saveCustomer(Customer customer);
    Customer getCustomerById(Long id);
    Customer findCustomerbyId(Long id);
    void updateCustomer(Customer customer);
    Page<Customer> getAllCustomerPageable(Pageable pageable , String name, String phone);
}
