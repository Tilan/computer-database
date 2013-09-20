package com.tilan.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tilan.domain.Computer;
import com.tilan.pagination.Pagination;
import com.tilan.service.ComputerService;
import com.tilan.service.manager.ServiceManager;

/**
 * Servlet implementation class UserServlet
 */

@WebServlet("/searchComputer")
public class ListingComputersBySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerService computerService;
	private int numPage = 1;
	private static final boolean PAGINER = true;
    public ListingComputersBySearch() {
        super();
        computerService = ServiceManager.INSTANCE.getComputerService();
    }

	/**
	 * La methode doGet est executee lorsqu'un client execute l'URI UserServlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Envoyer un objet dans la requete (la liste des computers)
		String name = request.getParameter("search");
		Pagination pagination=null;
		Boolean search = true;
		int numberOfComputers=0;
		
		if(isValideName(name)){
			pagination = computerService.findComputersByName(name, numPage, pagination.NB_COMPUTER_DEFAULT);
			numberOfComputers = pagination.getNbComputer();
			request.setAttribute("computers", pagination.getComputers());
			request.setAttribute("numPage", numPage);
			request.setAttribute("numberOfComputers", numberOfComputers);
			request.setAttribute("isSearch", search);
			request.setAttribute("search", name);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/dashboard.jsp"));
			rd.forward(request, response);
		}
	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations (en general formulaire) sur l'URI ComputerController
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	private boolean isValideName(String name){
		//Valide la syntaxe du nom utilisé pour la recherche
		if(name!=null&&!name.trim().isEmpty())
			return true;
		else
			return false;
	}
}
