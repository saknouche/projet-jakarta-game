package fr.sadev.app.servlets;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sadev.app.beans.Game;
import fr.sadev.app.repository.GameRepository;
import fr.sadev.app.utils.JPAUtil;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "home";
       
	EntityManagerFactory emf;
	GameRepository gameRepo;
       
	public void init() throws ServletException {
		emf = JPAUtil.getEntityManagerFactory("db_config");
		gameRepo = new GameRepository(emf, Game.class);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("idGame");
		Integer idGame = validationId(id);
		if(idGame != null) {
			gameRepo.deleteById(idGame);
			response.sendRedirect(VUE);
		}
	}

	private static Integer validationId(String id) {
		Integer idCat = null;
		// le NOT implique l'utilisation de && et == implique l'utilisation de ||
		if (id != null && id != "" && id.matches("^\\d+$")) {
			idCat = Integer.parseInt(id);
		}
		return idCat;
	}

}
