package com.tilan.dao;

import java.util.List;

import com.tilan.domain.Computer;
import com.tilan.pagination.Pagination;

/**
 * Handle the transition between the relational entity computer and the class object Computer using
 *
 */
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
	 *Called by the servlet, it permits to display the computer list, with only 15 elements per page
	 *@param request two param in order to set the first and the max result
	 *@return the pagination instance with the correct information 
	 */
	abstract Pagination findAll(int debut, int taille); 
	
	/**
	 * Same as before but with an attribute which permits to order the list
	 * @param attribute is the attribute on which we order the list
	 * @return the ordered pagination instance
	 */
	abstract Pagination findAll(int numPage, int compParPage, String attribute); 

	/**
	 *Called by the servlet, it permits to display the computer list considering a filter from search, with only 15 elements per page
	 *@param request two param in order to set the first and the max result
	 *@param the name with which the filtering will be done
	 *@return the pagination instance with the correct information 
	 */
	abstract Pagination findComputersByName (String name, int debut, int taille);
	
	@Deprecated
	abstract Pagination findAll(); 
	@Deprecated
	abstract Pagination findComputersByName (String name);
}
