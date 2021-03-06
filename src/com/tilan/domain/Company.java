package com.tilan.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * It is the class representing the equivalent of the company entity in the object world.
 * It is using the Hibernate libraries to handle the object-relational mapping 
 */
@Entity
@Table(name = "company")
public class Company {
	@Id
	@GeneratedValue
	private long id; 
	
	@Column(name="name")
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	
	/**
	 * It is implementing the Builder pattern that allows secure and easier build of company instances. 
	 */
	public static class Builder { 

		private Computer computer;
		
		public Builder() {
			computer = new Computer();
		}
		
		public Builder id(long id) {
			computer.setId(id);
			return this;
		}
		
		public Builder name(String name) {
			computer.setName(name);
			return this;
		}
	}
}