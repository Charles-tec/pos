package com.server.pos.models;


import com.server.pos.enums.StatusEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="pos")
public class Pos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="reference")
    private String reference;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private StatusEnum status;

}
