package com.example.tabletop.springboot.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PlayerKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "table_id")
    private Long tableId;

}
