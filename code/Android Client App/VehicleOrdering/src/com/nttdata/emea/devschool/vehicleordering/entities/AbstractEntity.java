package com.nttdata.emea.devschool.vehicleordering.entities;

public abstract class AbstractEntity
{
	private long id;
	
	protected AbstractEntity (long id)
	{
		this.id = id;
	}
	protected AbstractEntity()
	{
		
	}
	public void setId(long id)
	{
		this.id=id;
	}
	public long getId () {
		return id;
	}	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString ()
	{
		return this.getClass().getSimpleName() + " " + getId();
	}
}