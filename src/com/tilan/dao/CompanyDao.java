package com.tilan.dao;

import java.util.List;

import com.tilan.domain.Company;

public interface CompanyDao {
	List<Company> findAll (); 
	void create (Company company); 
}
