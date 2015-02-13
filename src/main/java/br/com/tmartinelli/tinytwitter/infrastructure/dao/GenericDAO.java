package br.com.tmartinelli.tinytwitter.infrastructure.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

public abstract class GenericDAO<T, ID> {

	protected EntityManager entityManager;
	
	private Class<T> clazz;
	
	public GenericDAO() {
		this(null, null);
	}
	
	public GenericDAO(EntityManager entityManager, Class<T> clazz) {
		this.entityManager = entityManager;
		this.clazz = clazz;
	}
	
	public List<T> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(clazz);
		TypedQuery<T> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
	}
	
	public T findById(ID id) {
		return entityManager.find(clazz, id);
	}
	
	@Transactional
	public void add(T entity) {
		entityManager.persist(entity);
	}
	
	@Transactional
	public void update(T entity) {
		entityManager.merge(entity);
	}
	
	@Transactional
	public void remove(T entity) {
		entityManager.remove(entity);
	}
}
