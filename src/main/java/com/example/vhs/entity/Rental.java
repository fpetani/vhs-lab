package com.example.vhs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

}
