package com.tilan.service.manager;

import com.tilan.service.ComputerService;
import com.tilan.service.Impl.ComputerServiceImpl;

public enum ServiceManager {

		INSTANCE;
		
		private ComputerService computerService;
		
		private ServiceManager () {
			computerService = new ComputerServiceImpl(); 
		}

		public ComputerService getComputerService() {
			return computerService;
		}
		
		
}
