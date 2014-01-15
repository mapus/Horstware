package com.nttdata.emea.devschool.vehicleordering.business;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="models")
public class Model extends AbstractEntity
{
	private String name;
	@ManyToOne private Type type;
	private String description;
	private String imageUrl;
	private long priceInEuroCent;
	
	public Model () {}
	
	public Model (String name, Type type, String description, String imageUrl, long priceInEuroCent)
	{
		this.name = name;
		this.type = type;
		this.description = description;
		this.imageUrl = imageUrl;
		this.priceInEuroCent = priceInEuroCent;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public long getPriceInEuroCent() {
		return priceInEuroCent;
	}
	public void setPriceInEuroCent(long priceInEuroCent) {
		this.priceInEuroCent = priceInEuroCent;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ (int) (priceInEuroCent ^ (priceInEuroCent >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Model other = (Model) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priceInEuroCent != other.priceInEuroCent)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	@Override
	public String toString ()
	{
		return super.toString() + ": " + name;
	}
}