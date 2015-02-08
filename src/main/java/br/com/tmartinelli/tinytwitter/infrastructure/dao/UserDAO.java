package br.com.tmartinelli.tinytwitter.infrastructure.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.tmartinelli.tinytwitter.domain.user.User;
import br.com.tmartinelli.tinytwitter.domain.user.UserRepository;
import br.com.tmartinelli.tinytwitter.domain.user.User_;

@RequestScoped
public class UserDAO extends GenericDAO<User, Long> implements UserRepository {

	@Inject
	public UserDAO(EntityManager entityManager) {
		super(entityManager, User.class);
	}

	@Override
	public User findByEmailAndPassword(User user) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.where(cb.equal(root.get(User_.email), user.getEmail()),
				 cb.equal(root.get(User_.password), user.getPassword()));
		TypedQuery<User> query = entityManager.createQuery(cq);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
