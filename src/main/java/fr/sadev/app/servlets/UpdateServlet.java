package fr.sadev.app.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sadev.app.beans.Game;
import fr.sadev.app.forms.EditGameForm;
import fr.sadev.app.repository.GameRepository;
import fr.sadev.app.utils.JPAUtil;


public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_FORM = "/WEB-INF/editGame.jsp";
	private static final String ATT_FORM = "form";
	private static final String ATT_GAME = "game";
       

	EntityManagerFactory emf;
	GameRepository gameRepo;
       
	public void init() throws ServletException {
		emf = JPAUtil.getEntityManagerFactory("db_config");
		gameRepo = new GameRepository(emf, Game.class);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idGame = request.getParameter("idGame");
		Integer id = validationId(idGame);
		if(id != null) {
			Game game = gameRepo.findById(id);
			request.setAttribute("game", game);
			request.getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EditGameForm form = new EditGameForm();
		Game game = form.editGame(request);
		request.setAttribute(ATT_GAME, game);
		request.setAttribute(ATT_FORM, form);
		request.getRequestDispatcher(VUE_FORM).forward(request, response);
	}
	
	
	private static Integer validationId(String id) {
		Integer idCat = null;
		//le NOT implique l'utilisation de && et == implique l'utilisation de ||
		if (id != null && id != "" && id.matches("^\\d+$")) {
			idCat = Integer.parseInt(id);
		}
		return idCat;
	}

}
