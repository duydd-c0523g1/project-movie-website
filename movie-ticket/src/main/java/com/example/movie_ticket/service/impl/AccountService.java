package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.Account;
import com.example.movie_ticket.repository.IAccountRepo;
import com.example.movie_ticket.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepo accountRepo;


    @Override
    public List<Account> findAllAccount() {
        return accountRepo.findAll();
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepo.findByUsername(username);
    }

    @Override
    public void signUpAccount(Account account) {
        accountRepo.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }
}
