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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String actionDone; 
		actionDone= request.getParameter("actionDone");
		
		if(actionDone.equals("Delete")){
			computerService.deleteComputerById(idComputerSelected);
		}
		else if(actionDone.equals("Edit")){
			
			//Get the computer instance
			Computer computer = computerService.findComputerById(idComputerSelected);
			
			//Get the edited attribute of the computer
			String name = request.getParameter("name");
			String introducedS = request.getParameter("introduced");
			String discontinuedS = request.getParameter("discontinued");
			String companyValue = request.getParameter("company"); 
			
			java.util.Date introduced=null, discontinued=null;
			try {
	            introduced = new SimpleDateFormat("yyyy-MM-dd").parse(introducedS);
	            discontinued = new SimpleDateFormat("yyyy-MM-dd").parse(discontinuedS);
	        } catch (ParseException e) {
	            System.err.println(e.getMessage());
	        }
			int company_id = Integer.parseInt(companyValue);
			Company company = null ; 
			
			if(company_id>0){
				company = companyService.findAll().get(company_id-1); 
			}
			
            // Update the data of the current computer
            computer.setName(name);
            computer.setIntroduced(introduced);
            computer.setDiscontinued(discontinued);
            computer.setCompany(company);

//			computer= new Computer.Builder().name(name).introduced(introduced).discontinued(discontinued).company(company).build(); 
			computerService.update(computer); 
		}
		
        // Return to the home page:
		response.sendRedirect(response.encodeRedirectURL("listAllComputers.aspx"));
		
	}

}
