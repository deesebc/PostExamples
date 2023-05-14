package com.home.example.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "BOARDGAME")
public class BoardGame extends PanacheEntity {

    //warning with PanacheEntity we dont need getter/setter or Id 
    public String designer;
    public String name;
    
}
