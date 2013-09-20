package com.tilan.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
 * Servlet implementation class AddComputer 
 * 
 * Allows the user to create and add a computer in the database
 * The servlet is checking the inputs entered.
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
	 * Called when a user click on the "add computer" button in the dashboard.jsp
	 * 
	 * @return the list of companies to addComputer.jsp
	 */
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("companies", companyService.findAll());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/addComputer.jsp"));
		rd.forward(request, response);
	}

	/**
	 * 
	 * Called when the user submit the form on the addComputer.jsp
	 * 
	 * @param request : get the attribute of the computer being created.
	 * 
	 * @return : the addComputer.jsp if the form wasn't valid
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//errors will contain all the eventual message error to be transmitted to the JSP from the validation process
		Map<String, String> errors = new HashMap<String, String>();
        
		//Get the edited attribute of the computer from the form
		String name = request.getParameter("name");
		String introducedS = request.getParameter("introduced");
		String discontinuedS = request.getParameter("discontinued");
		String companyValue = request.getParameter("company");
		
		Computer temp = new Computer.Builder().build(); 

			//Validation of these retrieved values
		try {
			validationName(name);
		} catch (Exception e1) {
			errors.put( "name", e1.getMessage() );
		}
		
		try {
			validationIntroduced(introducedS);
		} catch (Exception e1) {
			errors.put( "introduced", e1.getMessage() );
		}
		
		try {
			validationDiscontinued(discontinuedS);
		} catch (Exception e1) {
			errors.put( "discontinued", e1.getMessage() );
		}
		
		 if ( !errors.isEmpty() ) {// If the form is not valid
	        	
	        	//Display the computer state before the wrong modification with the corresponding error message(s)
	    		request.setAttribute("companies", companyService.findAll());
	        	request.setAttribute( "errors", errors );
	        	
	        	/* Transmit request/response to the JSP if the form is not valid */
	        	RequestDispatcher rd = getServletContext().getRequestDispatcher(
	    				response.encodeURL("/WEB-INF/addComputer.jsp"));
	    		rd.forward(request, response);
		 }
		 else{
			 
		    //Retrieve dates
			java.util.Date introduced = null, discontinued = null;
			try {
				introduced = new SimpleDateFormat("yyyy-MM-dd").parse(introducedS);
				discontinued = new SimpleDateFormat("yyyy-MM-dd").parse(discontinuedS);
			} catch (ParseException e) {
				System.err.println(e.getMessage());
			}
			
			//Retrieve company
			int company_id = Integer.parseInt(companyValue);
			Company company = null;
			if (company_id > 0) 
				company = companyService.findAll().get(company_id - 1);
	
			//Add computer on the database
			computerService.create(new Computer.Builder()
						.name(name.trim())
						.introduced(introduced).discontinued(discontinued)
						.company(company).build());
	
			response.sendRedirect(response.encodeRedirectURL("listAllComputers.aspx"));
		}
	}
	
	
	/**
	 *Check if the name is not empty
	 * 
	 *@param name : Name to be validated
	 */
	private void validationName(String name) throws Exception {
		if (name != null && name.trim().length() != 0) {
			return;
		} else {
			throw new Exception("Please enter a name");
		}

	}
	
	/**
	 * Check if the introduced date is not empty or not valid
	 * 
	 * @param date : Date to be validated
	 */

	private void validationIntroduced(String date) throws Exception {
		if (date != null && date.trim().length() != 0) {
			if (!date.matches("(\\d{4})[-](\\d{2})[-](\\d{2})")) {
				throw new Exception("Please enter a valid introduced date");
			}
		} else {
			throw new Exception("Please enter a introduced date");
		}
	}
	
	/**
	 * Check if the discontinued date is not empty or not valid
	 * 
	 * @param date : Date to be validated
	 */
	
	private void validationDiscontinued(String date) throws Exception {
		if (date != null && date.trim().length() != 0) {
			if (!date.matches("(\\d{4})[-](\\d{2})[-](\\d{2})")) {
				throw new Exception("Please enter a valid discontinued date");
			}
		} else {
			throw new Exception("Please enter a discontinued date");
		}
	}



}
