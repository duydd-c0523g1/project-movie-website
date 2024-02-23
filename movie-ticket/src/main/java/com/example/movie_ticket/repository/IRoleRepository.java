package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Long> {
    Role findByNameRole(String nameRole);
}
