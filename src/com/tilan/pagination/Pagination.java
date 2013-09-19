package com.tilan.pagination;

import java.util.List;

import com.tilan.domain.Computer;
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
