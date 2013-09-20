package com.tilan.service;

import java.util.List;

import com.tilan.domain.Company;

public interface CompanyService {
		/**
		 * Retrieve all the companies from the database
		 * 
		 * @return the list of all the companies of the database
		 */
		abstract List <Company> findAll(); 
}
