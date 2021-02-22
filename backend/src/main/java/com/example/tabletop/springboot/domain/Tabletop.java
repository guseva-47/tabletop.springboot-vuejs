package com.example.tabletop.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
public class Tabletop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private GSystem gameSystem;

    @ManyToOne
    private User owner;

//    @ManyToMany
//    @JoinTable(
//        name = "players",
//        joinColumns = @JoinColumn(name = "player_id"),
//        inverseJoinColumns = @JoinColumn(name = "table_id")
//    )
//    private Set<User> users;

//    @JsonIgnore
//    @OneToMany(mappedBy = "tabletop", cascade = {CascadeType.ALL})
//    private Set<Player> players;
}
