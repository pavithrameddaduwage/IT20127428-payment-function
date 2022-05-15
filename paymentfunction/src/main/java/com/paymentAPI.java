package com;
import model.pay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
/**
 * Servlet implementation class paymentAPI
 */
@WebServlet("/paymentAPI")
public class paymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	pay itemObj = new pay(); 

       
     
    public paymentAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String output = itemObj.insertItem(request.getParameter("userID"), 
				 request.getParameter("Amount"), 
				request.getParameter("paymenttype"), 
				request.getParameter("Date")); 
				response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		String output = itemObj.updateItem(paras.get("hidpaymentIDSave").toString(), 
		paras.get("userID").toString(), 
		paras.get("Amount").toString(), 
		paras.get("paymenttype").toString(), 
		paras.get("Date").toString()); 
		response.getWriter().write(output); 
	
	}

	 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 String output = itemObj.deleteItem(paras.get("paymentID").toString());
		 
		response.getWriter().write(output); 
	}
	private Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>(); 
		try
		 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
		 String queryString = scanner.hasNext() ? 
		 scanner.useDelimiter("\\A").next() : ""; 
		 scanner.close(); 
		 String[] params = queryString.split("&"); 
		 for (String param : params) 
		 {  
		 String[] p = param.split("="); 
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
	}

}
