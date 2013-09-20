package com.tilan.service;

import com.tilan.domain.Computer;
import com.tilan.pagination.Pagination;

public interface ComputerService {

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
		 * @param debut : first element to display
		 * @param taille : defines the last element to display
		 * @return pagination instance with the information for paginate
		 */
		abstract Pagination findAll(int debut, int taille); 
		
		/**
		 * 
		 * @param numPage is the current page
		 * @param compParPage is the number of element to display per page
		 * @param attribute is the attribute on which to order
		 * @return pagination instance with the information for paginate
		 */
		
		abstract Pagination findAll(int numPage, int compParPage, String attribute); 
		/**
		 * 
		 * @param name to search
		 * @param debut of the element to display
		 * @param taille defines the last element to display
		 * @return pagination instance with the information for paginate
		 */
		
		abstract Pagination findComputersByName (String name, int debut, int taille);
		
		
		abstract Pagination findAll(); 
		
		abstract Pagination findComputersByName (String name);
}
