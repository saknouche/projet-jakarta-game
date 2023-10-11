package fr.sadev.app.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sadev.app.beans.User;
import fr.sadev.app.forms.RegisterForm;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_FORM = "/WEB-INF/registerUser.jsp";
	private static final String VUE_CONNECTION = "/connect";
	private static final String ATT_FORM = "form";
	private static final String ATT_USER= "user";
       

    public RegisterServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RegisterForm form = new RegisterForm();
		User user = form.registerUser(request);
		
		if(form.getErreurs().isEmpty()) {
			response.sendRedirect(request.getContextPath() + VUE_CONNECTION);
		}else {
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_USER, user);
			request.getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}

}
