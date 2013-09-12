package com.tilan.dao.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tilan.dao.CompanyDao;
import com.tilan.dao.ComputerDao;
import com.tilan.dao.impl.CompanyDaoImpl;
import com.tilan.dao.impl.ComputerDaoImpl;

public enum DaoManager {

	INSTANCE;

	private ComputerDao computerDao;
	private CompanyDao companyDao; 
	private EntityManagerFactory emf;
	
	private DaoManager() {
		emf = Persistence.createEntityManagerFactory("tilanPU");
		computerDao = new ComputerDaoImpl();
		companyDao = new CompanyDaoImpl();
	}
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public ComputerDao getComputerDao() {
		return computerDao;
	}

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

}

