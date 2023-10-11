package fr.sadev.app.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sadev.app.beans.Game;
import fr.sadev.app.forms.AddGameForm;

public class InsertGamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_FORM = "/WEB-INF/insertGames.jsp";
	private static final String ATT_FORM = "form";
	private static final String ATT_GAME = "game";

	public InsertGamesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AddGameForm form = new AddGameForm();
		Game game = form.addGame(request);

		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_GAME, game);
		request.getRequestDispatcher(VUE_FORM).forward(request, response);

	}

}
