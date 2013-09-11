package com.tilan.dao;

import java.util.List;

import com.tilan.domain.Computer;

public interface ComputerDao {
	 List<Computer> getComputers ();

	void create(Computer computer); 
}
