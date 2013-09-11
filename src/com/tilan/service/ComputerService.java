package com.tilan.service;

import java.util.List;

import com.tilan.domain.Computer;

public interface ComputerService {

		abstract List <Computer> getComputers(); 
		void create (Computer computer); 
}
