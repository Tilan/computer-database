package com.tilan.pagination;

import java.util.List;
import com.tilan.domain.Computer;

/**
 * It is the class we use everytime we need to paginate results
 * @param nbComputer : total of computer corresponding to the search or total list if not in a search
 * @param computers: the computer list corresponding to the search or the full list if not in a search
 * @param NB_COMPUTER_DEFAULT : set the default number of computer to display on a page
 * 
 */
public class Pagination {
	public static final int NB_COMPUTER_DEFAULT=15;
	public static int nbComputer;
	
	private List<Computer> computers;

	public Pagination(List<Computer> computers){
		this.computers=computers;
	}

	public static int getNbComputer() {
		return nbComputer;
	}

	public static void setNbComputer(int nbComputer) {
		Pagination.nbComputer = nbComputer;
	}

	public List<Computer> getComputers() {
		return computers;
	}

	public void setComputers(List<Computer> computers) {
		this.computers = computers;
	}
	
}
