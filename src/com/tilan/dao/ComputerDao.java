package com.tilan.dao;

import java.util.List;

import com.tilan.domain.Computer;

public interface ComputerDao {
	 List<Computer> findAll ();
	 void create(Computer computer); 
}
