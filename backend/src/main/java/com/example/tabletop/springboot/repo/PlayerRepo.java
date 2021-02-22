package com.example.tabletop.springboot.repo;

import com.example.tabletop.springboot.domain.Player;
import com.example.tabletop.springboot.domain.PlayerKey;
import com.example.tabletop.springboot.domain.Tabletop;
import com.example.tabletop.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PlayerRepo extends JpaRepository<Player, PlayerKey> {
    Set<Player> findAllByTabletop (Tabletop tabletop);
    Set<Player> findAllByUser (User user);
}
