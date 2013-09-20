package com.tilan.service.manager;

import com.tilan.service.CompanyService;
import com.tilan.service.ComputerService;
import com.tilan.service.Impl.CompanyServiceImpl;
import com.tilan.service.Impl.ComputerServiceImpl;

/**
 *	Manage the services of the application.
 * The services are offering services to the Controllers.
 * The services are using the services of the Daos. 
 *
 * Implements the singleton design pattern (only one instance of the class possible) 
 *
 */
public enum ServiceManager {

		INSTANCE;//Only one instance in the enum (Implements the singleton pattern)
		
		private ComputerService computerService;
		private CompanyService companyService;
		
		/**
		 * Build the service manager instance
		 */
		private ServiceManager () {
			computerService = new ComputerServiceImpl(); 
			companyService = new CompanyServiceImpl(); 
		}

		/**
		 * Retreive the computer service attribute
		 * 
		 * @return the computer service attribute
		 */
		public ComputerService getComputerService() {
			return computerService;
		}
		
		/**
		 * Retreive the company service attribute
		 * 
		 * @return the company service attribute
		 */
		public CompanyService getCompanyService() {
			return companyService;
		}
		
		
		
}
