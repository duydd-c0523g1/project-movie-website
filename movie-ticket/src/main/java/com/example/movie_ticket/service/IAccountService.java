package com.example.movie_ticket.service;

import com.example.movie_ticket.model.Account;
import com.example.movie_ticket.model.Employee;

import java.util.List;

public interface IAccountService {

    List<Account> findAllAccount();
    Account findByUsername(String username);
    void signUpAccount(Account account);

    List<Account> getAllAccounts();
}
