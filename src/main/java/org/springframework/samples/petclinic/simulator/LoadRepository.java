package org.springframework.samples.petclinic.simulator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LoadRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public int generateLoad(int seq) {
		return entityManager.createNativeQuery("INSERT INTO LOAD_GENERATE SELECT ID FROM GENERATE_SERIES(1, :seq) ID")
				.setParameter("seq", seq).executeUpdate();
	}

}
