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
 * Servlet implementation class ListingAllComputers
 * 
 * The servlet launched by default from the index.jsp
 * It is listing all the computers of the database
 * Its view is the dashboard.jsp
 * 		It allows sort for each of the attributes of Computer
 * 		It allows access to add and edit mode of computers
 * 		It is organizing the computers list with a pagination system.
 * 		It allows search of a computers name. 
 */

@WebServlet("/listAllComputers.aspx")
public class ListingAllComputers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final boolean PAGINER = true;
	private ComputerService computerService;

	private int numPage = 1;	//Number of the page initialized to 1
	private String attribute=null; // Attribute of Computer that will sort the computer's list
	
	
    public ListingAllComputers() {
        super();
        computerService = ServiceManager.INSTANCE.getComputerService();
    }

	/**
	 * Called when the computer database application is launched
	 * 
	 * @param request : Contains the attribute from which the list should eventually be sorted by. 
	 * 
	 * @return the list of computers, the page to be displayed and the number of computers in the JSP dashboard
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Pagination pagination= null;
		Boolean search = false;
		
		if (request.getParameter("page") != null){
			numPage = Integer.parseInt(request.getParameter("page"));
		}
		attribute = request.getParameter("attribute"); 
		
			//Retreive the list of computers into a pagination instance regarding of the attribute parameter
		if(attribute==null)
			pagination = computerService.findAll(numPage,Pagination.NB_COMPUTER_DEFAULT);
		else if (attribute.equals("company")){
			pagination = computerService.findAll(numPage,Pagination.NB_COMPUTER_DEFAULT,attribute+".name");
		}
		else {
			pagination = computerService.findAll(numPage,Pagination.NB_COMPUTER_DEFAULT,attribute);
		}
		
			//Make the request for returning the list of computers, the page to be displayed and the number of computers in the JSP dashboard
		request.setAttribute("computers", pagination.getComputers());
		request.setAttribute("numPage", numPage);
		request.setAttribute("numberOfComputers", pagination.getNbComputer());
		request.setAttribute("isSearch", search);
		request.setAttribute("tri", attribute);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/dashboard.jsp"));
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
}
