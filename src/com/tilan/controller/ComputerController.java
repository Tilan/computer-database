package com.tilan.controller;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ParseConversionEvent;

import com.tilan.domain.Computer;
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
//		String name = request.getParameter("name");
//		String introducedS = request.getParameter("introduced");
//		String discontinuedS = request.getParameter("discontinued");
//		String company = request.getParameter("company"); 
//		
//		Date introduced=null, discontinued=null;
//		try {
//			introduced = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(introducedS);
//			discontinued = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(discontinuedS);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		if(name!=null&& !name.isEmpty()){
//			System.out.println("Le nom saisie est " + name);
//			computerService.create(new Computer.Builder().name(name).introduced(introduced).discontinued(discontinued).build());
//		}
//		else 
//			System.out.println("Le nom saisie est vide");
	}
	
}
