package com.nttdata.emea.devschool.vehicleordering.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class TypeDAO extends AbstractDAO<Type>
{
	public List<Type> retrieveAll ()
	{
		String jpql = "SELECT t FROM Type AS t";
		Query query = entityManager.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Type> result = query.getResultList();
		return result;
	}
	
	public Type findById (Long id)
	{
		String jpql =
			"SELECT t " + 
			"FROM Type " +
			"AS t " + 
			"WHERE t.id=?1";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, id);
		
		@SuppressWarnings("unchecked")
		List<Type> result = query.getResultList();
		if(!result.isEmpty())
		{
			return result.get(0);
		}
		else
		{
			return null;
		}
	}
}