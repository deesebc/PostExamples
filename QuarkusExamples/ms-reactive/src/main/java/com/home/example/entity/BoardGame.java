package com.home.example.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.hibernate.reactive.panache.common.WithSessionOnDemand;
//Warning: we modify the package of PanacheEntity
//import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "BOARDGAME")
@WithSessionOnDemand
public class BoardGame extends PanacheEntity {

    //warning with PanacheEntity we dont need getter/setter or Id 
    public String designer;
    public String name;

    public String getDesigner() {
        return designer;
    }
    public String getName() {
        return name;
    }
    public void setDesigner(String designer) {
        this.designer = designer;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "BoardGame [id=" + id + ", designer=" + designer + ", name=" + name + "]";
    }   
    
}
