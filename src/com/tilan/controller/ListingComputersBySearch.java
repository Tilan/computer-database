package com.tilan.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tilan.service.ComputerService;
import com.tilan.service.manager.ServiceManager;

/**
 * Servlet implementation class UserServlet
 */

@WebServlet("/searchComputer")
public class ListingComputersBySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerService computerService;

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
		if(isValideName(name)){
			request.setAttribute("computers", computerService.findComputersByName(name));
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
