package com.example.tabletop.springboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(UserViews.Id.class)
    private Long id;

    @JsonView(UserViews.IdEmail.class)
    private String email;

    @JsonView(UserViews.FullUser.class)
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(UserViews.FullUser.class)
    private LocalDateTime lastActiveAt;

//    @ManyToMany(mappedBy = "users")
//    private Set<Tabletop> tabletops;


//    @JsonIgnore
//    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
//    Set<Player> players;
}
