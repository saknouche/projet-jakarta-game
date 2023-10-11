package fr.sadev.app.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sadev.app.beans.Game;
import fr.sadev.app.beans.User;
import fr.sadev.app.repository.GameRepository;
import fr.sadev.app.repository.UserRepository;
import fr.sadev.app.utils.JPAUtil;


public class HomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VUE_HOME = "/WEB-INF/home.jsp";
	private static final String ATT_GAMES = "games";
	private String message = "message";

	EntityManagerFactory emf;
	GameRepository gameRepo;
	UserRepository userRepo;
       
	public void init() throws ServletException {
		emf = JPAUtil.getEntityManagerFactory("db_config");
		gameRepo = new GameRepository(emf, Game.class);
		userRepo = new UserRepository(emf, User.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User u = userRepo.getGamesByUserId(2);
		for (Game g : u.getGames()) {
			System.out.println(g);
		}
		
		
		List<Game> games = gameRepo.findAll();
		
		if(!games.isEmpty()) {
			request.setAttribute(ATT_GAMES, games);
			request.getRequestDispatcher(VUE_HOME).forward(request, response);
		}else {
			message = "La liste de jeux est vide !";
			request.setAttribute("message", message);
			request.getRequestDispatcher(VUE_HOME).forward(request, response);
		}
	}

}
