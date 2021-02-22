package com.example.tabletop.springboot.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Player {
    @EmbeddedId
    private PlayerKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("tableId")
    @JoinColumn(name = "table_id")
    private Tabletop tabletop;
}
