package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepo extends JpaRepository<Account,Long> {
    Account findByUsername(String username);
}
