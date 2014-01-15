package com.nttdata.emea.devschool.vehicleordering.business;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="types")
public class Type extends AbstractEntity
{
	private String name;
	
	public Type () {}
	
	public Type (String name)
	{
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString ()
	{
		return super.toString() + ": " + name;
	}
}