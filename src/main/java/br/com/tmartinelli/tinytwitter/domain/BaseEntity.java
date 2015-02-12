package br.com.tmartinelli.tinytwitter.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !getClass().equals(obj.getClass()) || id == null) {
			return false;
		}
		return id.equals(((BaseEntity) obj).id) ? true : false;
	}
}
