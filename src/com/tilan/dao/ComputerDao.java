package com.tilan.dao;

import java.util.List;

import com.tilan.domain.Computer;

public interface ComputerDao {
	 abstract List<Computer> findAll ();
	 abstract List<Computer> findComputersByName (String name); 
	 abstract void create(Computer computer); 
}
