package com.nttdata.emea.devschool.vehicleordering.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
public class ModelDAO extends AbstractDAO<Model>
{
	public Model findById (Long id)
	{
		String jpql =
			"SELECT m " + 
			"FROM Model " +
			"AS m " + 
			"WHERE m.id=?1";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, id);
		
		@SuppressWarnings("unchecked")
		List<Model> result = query.getResultList();
		if(!result.isEmpty())
		{
			return result.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public List<Model> findByType (Type type)
	{
		String jpql =
			"SELECT m " + 
			"FROM Model " +
			"AS m";
		
		if(type != null)
		{
			jpql += " WHERE m.type=?1";
		}
		
		Query query = entityManager.createQuery(jpql);
		
		if(type != null)
		{
			query.setParameter(1, type);
		}
		
		@SuppressWarnings("unchecked")
		List<Model> result = query.getResultList();
		return result;
	}
}