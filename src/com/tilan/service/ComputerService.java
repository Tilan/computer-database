package com.tilan.service;

import com.tilan.domain.Computer;
import com.tilan.pagination.Pagination;

public interface ComputerService {

		abstract Pagination findAll(); 
		abstract Pagination findAll(int debut, int taille); 
		abstract void create (Computer computer); 
		abstract Pagination findComputersByName (String name);
		abstract Computer findComputerById (long id);
		abstract Pagination findComputersByName (String name, int debut, int taille);
		abstract void deleteComputerById(long id);
		abstract void update(Computer computer); 
}
