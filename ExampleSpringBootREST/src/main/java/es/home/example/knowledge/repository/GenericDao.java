package es.home.example.knowledge.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import es.home.example.knowledge.entity.GenericEntity;

@NoRepositoryBean
public interface GenericDao<E extends GenericEntity<K>, K extends Serializable>
		extends JpaRepository<E, K>, JpaSpecificationExecutor<E> {

}
