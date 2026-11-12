package com.tecnocomfenalco.edurag.eduoptima_ai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Assigment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Assigment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}