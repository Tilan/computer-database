package com.tilan.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.ejb.criteria.expression.function.LengthFunction;
import org.hibernate.metamodel.relational.Size;

import com.tilan.domain.Computer;
import com.tilan.service.ComputerService;
import com.tilan.service.manager.ServiceManager;

/**
 * Servlet implementation class UserServlet
 */

@WebServlet("/listAllComputers")
public class ListingAllComputers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerService computerService;

    public ListingAllComputers() {
        super();
        computerService = ServiceManager.INSTANCE.getComputerService();
    }

	/**
	 * La methode doGet est executee lorsqu'un client execute l'URI UserServlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Envoyer un objet dans la requete (la liste des computers)
		
		List <Computer> computers = computerService.findAll();
		int numberOfComputers = computers.size();
		request.setAttribute("computers", computers);
		request.setAttribute("numberOfComputers", numberOfComputers);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/dashboard.jsp"));
		rd.forward(request, response);
	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations (en general formulaire) sur l'URI ComputerController
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
}
