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
import com.tilan.pagination.Pagination;
import com.tilan.service.ComputerService;
import com.tilan.service.manager.ServiceManager;

/**
 * Servlet implementation class UserServlet
 */

@WebServlet("/listAllComputers.aspx")
public class ListingAllComputers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final boolean PAGINER = true;
	private ComputerService computerService;

	private int numPage = 1;	//Numéro de page, 1 par défaut
	private int compParPage = 15; //Nombre de résultat par page, 15 par défaut
    public ListingAllComputers() {
        super();
        computerService = ServiceManager.INSTANCE.getComputerService();
    }

	/**
	 * La methode doGet est executee lorsqu'un client execute l'URI UserServlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Envoyer un objet dans la requete (la liste des computers)
		List<Computer> computers = null;
		Pagination pagination;
		//Récuparation du numéro de la page
		if (request.getParameter("page") != null){
			numPage = Integer.parseInt(request.getParameter("page"));
		}
		
		pagination = computerService.findAll(numPage,compParPage);
		int numberOfComputers = pagination.getNbComputer();
		request.setAttribute("computers", pagination.getComputers());
		request.setAttribute("numPage", numPage);
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
