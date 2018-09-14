package es.home.example.dao;

import java.io.Serializable;
import java.util.List;

import es.home.example.pojo.GenericEntity;

public interface AbstractRepository<P extends GenericEntity<? extends Serializable>, K> {

	void create(P p);

	P findById(K id);

	List<P> getResultList();

	void remove(P t);

}
