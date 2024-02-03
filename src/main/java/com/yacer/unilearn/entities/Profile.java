package com.yacer.unilearn.entities;

import jakarta.persistence.*;

@Entity
public class Profile {
    @Id
    Integer id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    User user;
}
