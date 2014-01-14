package com.nttdata.emea.devschool.vehicleordering.entities;

public class VehicleType
{
	private String name;
	private long id;
	
	public VehicleType (long id, String name)
	{
		this.id=id;
		this.name = name;
	}
	public VehicleType()
	{
		
	}
	public void setId(long id)
	{
		this.id=id;
	}
	public long getId () {
		return id;
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
		VehicleType other = (VehicleType) obj;
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