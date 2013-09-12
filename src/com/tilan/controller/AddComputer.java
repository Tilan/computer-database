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

import com.tilan.domain.Computer;
import com.tilan.service.CompanyService;
import com.tilan.service.ComputerService;
import com.tilan.service.manager.ServiceManager;

/**
 * Servlet implementation class addComputer
 */
@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ComputerService computerService;
	private CompanyService companyService;
	
    public AddComputer() {
        super();
        computerService = ServiceManager.INSTANCE.getComputerService();
        companyService = ServiceManager.INSTANCE.getCompanyService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("companies", companyService.findAll());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/addComputer.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("name");
		String introducedS = request.getParameter("introduced");
		String discontinuedS = request.getParameter("discontinued");
		String company = request.getParameter("company"); 
		
		java.util.Date introduced=null, discontinued=null;
		try {
            introduced = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("introduced"));
            discontinued = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("discontinued"));
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
//		System.out.println("Le nom saisie est " + name);
		if(name!=null&& !name.isEmpty()){
			computerService.create(new Computer.Builder().name(name).introduced(introduced).discontinued(discontinued).build());
		}
		else 
			System.out.println("Le nom saisie est vide");
		
		request.setAttribute("computers", computerService.findAll());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/dashboard.jsp"));
		rd.forward(request, response);
	}

}
