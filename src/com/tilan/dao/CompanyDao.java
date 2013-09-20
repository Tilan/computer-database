package com.tilan.dao;

import java.util.List;

import com.tilan.domain.Company;

public interface CompanyDao {
	
	/**
	 * Retrieve all the companies from the database
	 * 
	 * @return the list of all the companies of the database
	 */
	abstract List <Company> findAll(); 
	
}
