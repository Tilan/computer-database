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
	
	/**
	 * 
	 * @param debut is the first element to display in the paginate list
	 * @param taille permits to calc the last element to display in the paginate list
	 * @return the pagination instance which contains the computers list and the informations needed to paginate
	 */
	abstract Pagination findAll(int debut, int taille); 
	
	/**
	 * 
	 * @param numPage indicates the actual page Number
	 * @param compParPage indicates the number of element to display on each page
	 * @param attribute indicates the attribute to order the list
	 * @return
	 */
	
	abstract Pagination findAll(int numPage, int compParPage, String attribute); 
	
	/**
	 * 
	 * @param name is the string to search
	 * @param debut is the first element to display in the paginate list
	 * @param taille permits to calc the last element to display in the paginate list
	 * @return
	 */
	abstract Pagination findComputersByName (String name, int debut, int taille);
	
	abstract Pagination findAll(); 

	abstract Pagination findComputersByName (String name);
}
