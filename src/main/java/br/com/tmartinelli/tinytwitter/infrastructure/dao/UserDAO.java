package br.com.tmartinelli.tinytwitter.infrastructure.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.com.tmartinelli.tinytwitter.domain.user.User;
import br.com.tmartinelli.tinytwitter.domain.user.UserRepository;
import br.com.tmartinelli.tinytwitter.domain.user.User_;

@RequestScoped
public class UserDAO extends GenericDAO<User, Long> implements UserRepository {

	public UserDAO() {
		this(null);
	}
	
	@Inject
	public UserDAO(EntityManager entityManager) {
		super(entityManager, User.class);
	}

	@Override
	public User findByEmailAndPassword(User user) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		
		query.where(builder.equal(root.get(User_.email), user.getEmail()),
				 builder.equal(root.get(User_.password), user.getPassword()));
		try {
			return entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public List<User> findByName(String name) {
		String nameParam = new StringBuilder().append("%").append(name)
				.append("%").toString().toLowerCase();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		
		query.where(builder.like(builder.lower(root.get(User_.name)), nameParam));
		
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public User findFullById(Long id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);

		Root<User> root = query.from(User.class);
		root.fetch(User_.followers, JoinType.LEFT);
		
		query.where(builder.equal(root.get("id"), id));
		
		return entityManager.createQuery(query).getSingleResult();
	}
}
