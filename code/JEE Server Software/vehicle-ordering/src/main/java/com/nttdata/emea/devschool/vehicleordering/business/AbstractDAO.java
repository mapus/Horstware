package com.nttdata.emea.devschool.vehicleordering.business;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractDAO<T>
{
	@PersistenceContext(unitName="vehicleOrdering")
	protected EntityManager entityManager;
	
	public void persist (T entity)
	{
		entityManager.persist(entity);
	}
}
