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

import com.tilan.domain.Company;
import com.tilan.domain.Computer;
import com.tilan.service.CompanyService;
import com.tilan.service.ComputerService;
import com.tilan.service.manager.ServiceManager;

/**
 * 
 * Servlet implementation class addComputer
 */
@WebServlet("/addComputer.aspx")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("companies", companyService.findAll());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/addComputer.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String introducedS = request.getParameter("introduced");
		String discontinuedS = request.getParameter("discontinued");
		String companyValue = request.getParameter("company");

		java.util.Date introduced = null, discontinued = null;
		try {
			introduced = new SimpleDateFormat("yyyy-MM-dd").parse(introducedS);
			discontinued = new SimpleDateFormat("yyyy-MM-dd")
					.parse(discontinuedS);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}

		int company_id = Integer.parseInt(companyValue);
		Company company = null;
		if (company_id > 0) {
			company = companyService.findAll().get(company_id - 1);
		}

		if (name != null && !name.isEmpty()) {
			computerService.create(new Computer.Builder().name(name)
					.introduced(introduced).discontinued(discontinued)
					.company(company).build());
		}

		request.setAttribute("computers", computerService.findAll());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/dashboard.jsp"));
		rd.forward(request, response);
	}



}
