package com.tilan.service.Impl;

import java.util.List;

import com.tilan.dao.CompanyDao;
import com.tilan.dao.manager.DaoManager;
import com.tilan.domain.Company;
import com.tilan.service.CompanyService;

public class CompanyServiceImpl implements CompanyService{

	private CompanyDao companyDao; 
	
	public CompanyServiceImpl() {
		companyDao = DaoManager.INSTANCE.getCompanyDao();
	}
	
	@Override
	public List<Company> findAll() {
		return companyDao.findAll();
	}

}
