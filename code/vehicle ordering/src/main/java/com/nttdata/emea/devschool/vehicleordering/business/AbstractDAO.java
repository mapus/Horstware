package com.nttdata.emea.devschool.vehicleordering.business;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractDAO<DedicatedEntity extends AbstractEntity> {
	
	@PersistenceContext(unitName = "vehicleOrdering")
	private EntityManager entityManager;
	
	public Query createQuery(String queryExpression){
		return entityManager.createQuery(queryExpression);
	}
	
	public void persist(DedicatedEntity entity){
		entityManager.persist(entity);
	}

}
