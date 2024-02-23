package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.Account;
import com.example.movie_ticket.model.MyUserDetail;
import com.example.movie_ticket.repository.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private IAccountRepo accountRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("Username " + username + "không tồn tại");
        }
        return new MyUserDetail(account);
    }
}
