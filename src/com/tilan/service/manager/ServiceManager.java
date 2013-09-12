package com.tilan.service.manager;

import com.tilan.service.CompanyService;
import com.tilan.service.ComputerService;
import com.tilan.service.Impl.CompanyServiceImpl;
import com.tilan.service.Impl.ComputerServiceImpl;

public enum ServiceManager {

		INSTANCE;
		
		private ComputerService computerService;
		private CompanyService companyService;
		
		private ServiceManager () {
			computerService = new ComputerServiceImpl(); 
			companyService = new CompanyServiceImpl(); 
		}

		public ComputerService getComputerService() {
			return computerService;
		}
		
		public CompanyService getCompanyService() {
			return companyService;
		}
		
		
		
}
