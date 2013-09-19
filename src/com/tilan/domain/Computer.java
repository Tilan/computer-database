package com.tilan.domain;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "computer")
@NamedQuery(name = "findAllComputers", query = "SELECT comp FROM Computer comp")

public class Computer {
	@Id
	@GeneratedValue
	private long id; 
	
	@Column(name="name")
	private String name; 
	
	@Column(name="introduced")
	@Temporal(TemporalType.TIMESTAMP)
	private Date introduced; 
	
	@Column(name="discontinued")
	@Temporal(TemporalType.TIMESTAMP)
	private Date discontinued; 
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;

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

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		 this.introduced = introduced;

	}

	public String getIntroducedFormatted (){
		return getDateInNiceStringFormat(introduced); 
	}
	
	public Date getDiscontinued() {
		return discontinued;
	}
	
	public String getDiscontinuedFormatted (){
		return getDateInNiceStringFormat(discontinued); 
	}
	
	
	public String getDateInNiceStringFormat(Date date) 
	/**
	 * @author Christop
	 * @returns La date entrée à un format yyyy-MM-dd en string
	 */
	{
		String dateS=null; 
		if(date!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateS= date.toString();
			try {
				dateS= dateFormat.format(dateFormat.parse(dateS));
			} catch (ParseException e) {
				e.printStackTrace();
			}		
		}
		return dateS;
	}
	

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}  
	
	public static class Builder {
		private Computer computer;
		
		public Builder() {
			computer = new Computer();
		}
		
		public Builder(Computer comp) {
			computer = comp;
		}
		
		public Builder id(long id) {
			computer.setId(id);
			return this;
		}
		
		public Builder name(String name) {
			computer.setName(name);
			return this;
		}
		
		public Builder introduced(Date introduced ) {
			computer.setIntroduced(introduced);
			return this;
		}
		
		public Builder discontinued(Date discontinued ) {
			computer.setDiscontinued(discontinued);
			return this;
		}
		
		public Builder company (Company company ) {
			computer.setCompany(company);
			return this;
		}
		
		public Computer build() {
			return computer;
		}
	}

}
