package com.example.tabletop.springboot.repo;

import com.example.tabletop.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
}
