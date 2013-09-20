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
 * Servlet implementation class addComputer
 */
@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ComputerService computerService;
	private CompanyService companyService;
	private long idComputerSelected=1;

	
    public EditComputer() {
        super();
        computerService = ServiceManager.INSTANCE.getComputerService();
        companyService = ServiceManager.INSTANCE.getCompanyService();
    }

	/**
	 * Called when a user click on the name of a computer in the dashboard.jsp
	 * 
	 * @param request : get the computer instance to be modified and the list of companies 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idComputerSelectedS = request.getParameter("idComputerSelected");
		
		//Get the computer object to be edited
		idComputerSelected = Long.parseLong(idComputerSelectedS);
		Computer computer = computerService.findComputerById(idComputerSelected);   
		request.setAttribute("computer", computer); 
		
		//Get the company list
		request.setAttribute("companies", companyService.findAll());
		
		//Launch editComputer jsp with the request configured
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/editComputer.jsp"));
		rd.forward(request, response);
	}

	/**
	 * Called in the editComputer.jsp in order to delete or edit a computer
	 * 
	 * @param request : Contains the action done (Delete or Edit) 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String actionDone; 
		actionDone= request.getParameter("actionDone");
		
		if(actionDone.equals("Delete")){
			//Delete computer instance on database
			computerService.deleteComputerById(idComputerSelected);
			
	        //Return to the home page:
			response.sendRedirect(response.encodeRedirectURL("listAllComputers.aspx"));
		}
		else if(actionDone.equals("Edit")){
//			String resultat;
	        Map<String, String> errors = new HashMap<String, String>();
			
			//Get the computer instance
			Computer computer = computerService.findComputerById(idComputerSelected);
			
			//Get the edited attribute of the computer from the form
			String name = request.getParameter("name");
			String introducedS = request.getParameter("introduced");
			String discontinuedS = request.getParameter("discontinued");
			String companyValue = request.getParameter("company"); 
			
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
			
	        if ( !errors.isEmpty() ) {
	        	
	    		request.setAttribute("computer", computer); 
	    		request.setAttribute("companies", companyService.findAll());
	        	request.setAttribute( "errors", errors );
	        	
	        	/* Transmit request/response to the JSP if the form is not valid */
			    this.getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp" ).forward( request, response );
	            
	        } else {
	           
		        //Dates
				java.util.Date introduced=null, discontinued=null;
				try {
		            introduced = new SimpleDateFormat("yyyy-MM-dd").parse(introducedS);
		            discontinued = new SimpleDateFormat("yyyy-MM-dd").parse(discontinuedS);
		        } catch (ParseException e) {
		            System.err.println(e.getMessage());
		        }
				
				//Company
				int company_id = Integer.parseInt(companyValue);
				Company company = null ; 
				if(company_id>0)
					company = companyService.findAll().get(company_id-1); 
				
	            // Rebuild computer
				computer= new Computer.Builder(computer).name(name).introduced(introduced).discontinued(discontinued).company(company).build(); 
				
				//Update data in database 
				computerService.update(computer); 
				
			    response.sendRedirect(response.encodeRedirectURL("listAllComputers.aspx"));
			}
				
		}
		
	}
	
	private void validationName(String name) throws Exception {
		if (name != null && name.trim().length() != 0) {
			return;
		} else {
			throw new Exception("Please enter a name");
		}

	}

	private void validationIntroduced(String date) throws Exception {
		if (date != null && date.trim().length() != 0) {
			if (!date.matches("(\\d{4})[-](\\d{2})[-](\\d{2})")) {
				throw new Exception("Please enter a valid introduced date");
			}
		} else {
			throw new Exception("Please enter a introduced date");
		}
	}
	
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
