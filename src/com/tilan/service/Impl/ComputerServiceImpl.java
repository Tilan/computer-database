package com.tilan.service.Impl;

import com.tilan.dao.ComputerDao;
import com.tilan.dao.manager.DaoManager;
import com.tilan.domain.Computer;
import com.tilan.pagination.Pagination;
import com.tilan.service.ComputerService;

public class ComputerServiceImpl implements ComputerService{

	private ComputerDao computerDao; 
	
	public ComputerServiceImpl() {
		computerDao = DaoManager.INSTANCE.getComputerDao();
	}
	
	@Override
	public Pagination findAll(int debut, int taille) {
		return computerDao.findAll(debut, taille);
	}
	
	@Override
	public Pagination findComputersByName (String name, int debut, int taille) {
		return computerDao.findComputersByName (name, debut, taille);
	}

	@Override
	public void create(Computer computer) {
		computerDao.create(computer);
		
	}

	@Override
	public Pagination findAll() {
		return findAll(0,0);
	}

	@Override
	public Pagination findComputersByName(String name) {
		return findComputersByName(name, 0, 0);	
		}
	

}
