package com.home.example.springwebflux.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class GenericEntity<K extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected K id;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    protected K getId() {
	return id;
    }

    protected void setId(final K id) {
	this.id = id;
    }

}
