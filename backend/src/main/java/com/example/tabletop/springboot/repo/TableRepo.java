package com.example.tabletop.springboot.repo;

import com.example.tabletop.springboot.domain.Tabletop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepo extends JpaRepository<Tabletop, Long> {
}
