package fr.sadev.app.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.sadev.app.beans.User;
import fr.sadev.app.forms.ConnectionForm;


public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_FORM = "/WEB-INF/connection.jsp";
	private static final String VUE_HOME = "/home";
	private static final String ATT_FORM = "form";
	private static final String ATT_USER = "user";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ConnectionForm form = new ConnectionForm();
		User user = form.connectUser(request);		
		request.setAttribute(ATT_USER, user);
		HttpSession session = request.getSession();
		
		if(!form.getErreurs().isEmpty() || form.getResult() != null) {
			request.setAttribute(ATT_FORM, form);
			request.getRequestDispatcher(VUE_FORM).forward(request, response);
		}else {
			session.setAttribute(ATT_USER, user);
			response.sendRedirect(request.getContextPath() + VUE_HOME);
		}
	}

}
