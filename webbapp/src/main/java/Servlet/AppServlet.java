/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Isa
 */
public class AppServlet extends HttpServlet{
    
    private String title; 
    private String name; 
    
    public void init() throws ServletException{
        title = "välkommen";
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        name = request.getParameter("first_name"); 
        name += " " + request.getParameter("last_name"); 
        
        response.setContentType("text/html"); 
        PrintWriter out = response.getWriter(); 
        out.println("<HTML><HEAD><TITLE>" + title + "</TITLE></HEAD><BODY><TABLE>"); 
        out.println("<BODY><Hi alight = \"center\">" + title + name + "</HI></BODY></HTML>");
        
    }
}
