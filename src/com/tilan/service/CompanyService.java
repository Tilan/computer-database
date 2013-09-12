package com.tilan.service;

import java.util.List;

import com.tilan.domain.Company;

public interface CompanyService {

		abstract List <Company> findAll(); 
		abstract void create (Company company); 
}
