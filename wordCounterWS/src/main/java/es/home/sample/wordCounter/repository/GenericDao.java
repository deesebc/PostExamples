package es.home.sample.wordCounter.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import es.home.sample.wordCounter.entity.GenericEntity;

@NoRepositoryBean
public interface GenericDao<E extends GenericEntity<K>, K extends Serializable>
        extends JpaRepository<E, K>, JpaSpecificationExecutor<E> {

}
