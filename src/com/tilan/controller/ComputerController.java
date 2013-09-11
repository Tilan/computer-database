package com.tilan.controller;


import java.io.IOException;
import java.util.Date;

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

@WebServlet("/toto")
public class ComputerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerService computerService;

    public ComputerController() {
        super();
        computerService = ServiceManager.INSTANCE.getComputerService();
    }

	/**
	 * La methode doGet est executee lorsqu'un client execute l'URI UserServlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Envoyer un objet dans la requete (la liste des computers)
		request.setAttribute("computers", computerService.getComputers());
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/dashboard.jsp"));
		rd.forward(request, response);
	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations (en general formulaire) sur l'URI UserServlet
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String company = request.getParameter("company"); 
		
		
		
		//		String login = request.getParameter("login");
//		String password = request.getParameter("password");
//		
//		//Test de validite des champs login et password
//		if(login != null && !login.isEmpty() && password != null && !password.isEmpty())
//			userService.create(new User.Builder().login(login).password(password).build());
//		
//		//Redirection vers la page
//		doGet(request, response);
	}
	
}
