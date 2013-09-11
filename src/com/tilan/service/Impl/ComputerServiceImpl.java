package com.tilan.service.Impl;

import java.util.List;

import com.tilan.dao.ComputerDao;
import com.tilan.dao.manager.DaoManager;
import com.tilan.domain.Computer;
import com.tilan.service.ComputerService;

public class ComputerServiceImpl implements ComputerService{

	private ComputerDao computerDao; 
	
	public ComputerServiceImpl() {
		computerDao = DaoManager.INSTANCE.getComputerDao();
	}
	
	@Override
	public List<Computer> getComputers() {
		return computerDao.getComputers();
	}

	@Override
	public void create(Computer computer) {
		computerDao.create(computer);
		
	}
	

}
