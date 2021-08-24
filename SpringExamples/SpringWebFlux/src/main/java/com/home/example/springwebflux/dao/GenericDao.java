package com.home.example.springwebflux.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.home.example.springwebflux.entity.GenericEntity;

@NoRepositoryBean
public interface GenericDao<E extends GenericEntity<K>, K extends Serializable> extends JpaRepository<E, K>, JpaSpecificationExecutor<E> {

}
