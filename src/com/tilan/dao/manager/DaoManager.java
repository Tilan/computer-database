package com.tilan.dao.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tilan.dao.CompanyDao;
import com.tilan.dao.ComputerDao;
import com.tilan.dao.impl.CompanyDaoImpl;
import com.tilan.dao.impl.ComputerDaoImpl;
/**
 * Manager of the Daos that access to the database computer-database-db via the persitence unit tilanPU
 * Because of that EntityManager, the connection to the database is configured only once with a persistence unit (described in the persistence.xml file) 
 * Implements the singleton design pattern (only one instance of the class possible) 
 */
public enum DaoManager {

	INSTANCE;//Only one instance in the enum (Implements the singleton pattern)

	private ComputerDao computerDao;
	private CompanyDao companyDao; 
	private EntityManagerFactory emf;
	
	private DaoManager() {
		//Create the persistence unit with the persistence.xml file to configure the connection with the database
		emf = Persistence.createEntityManagerFactory("tilanPU");
		computerDao = new ComputerDaoImpl();
		companyDao = new CompanyDaoImpl();
	}
	
	
	/**
	 * Retrieve the entity manager instance
	 * @return the entity manager 
	 */
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	/**
	 * Retrieve the computerDao instance
	 * @return the computerDao instance
	 */
	public ComputerDao getComputerDao() {
		return computerDao;
	}
	
	/**
	 * Retrieve the companyDao instance
	 * @return the companyDao instance
	 */
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

}

