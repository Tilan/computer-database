package com.tilan.dao;

import java.util.List;

import com.tilan.domain.Computer;
import com.tilan.pagination.Pagination;

public interface ComputerDao {
	
	/**
	 * Add a computer instance on the database
	 * 
	 * @param computer to be added into the database
	 */

	abstract void create (Computer computer); 
	
	/**
	 * Update a computer instance on the database
	 * 
	 * @param computer to be updated into the database
	 */
	abstract void update(Computer computer);
	
	/**
	 * Delete a computer entity on the database
	 * 
	 * @param id of the computer to be deleted on the database
	 */
	abstract void deleteComputerById(long id);
	
	/**
	 * Retrieve a computer instance from the database
	 * 
	 * @param id of the computer to be retrieved on the database
	 * @return the computer instance associated with the input id
	 */
	abstract Computer findComputerById (long id);
	
	abstract Pagination findAll(int debut, int taille); 
	
	abstract Pagination findAll(int numPage, int compParPage, String attribute); 
	
	abstract Pagination findComputersByName (String name, int debut, int taille);
	
	@Deprecated
	abstract Pagination findAll(); 
	@Deprecated
	abstract Pagination findComputersByName (String name);
}
