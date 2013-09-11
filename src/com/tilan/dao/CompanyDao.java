package com.tilan.dao;

import java.util.List;

import com.tilan.domain.Company;

public interface CompanyDao {
	List<Company> getCompanies (); 
	void create (Company company); 
}
