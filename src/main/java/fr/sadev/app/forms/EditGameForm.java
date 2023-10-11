package fr.sadev.app.forms;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

import fr.sadev.app.beans.Game;
import fr.sadev.app.repository.GameRepository;
import fr.sadev.app.utils.JPAUtil;

public class EditGameForm {

	private static final String CHAMP_ID = "id";
	private static final String CHAMP_NAME = "name";
	private static final String CHAMP_DESCRIPTION = "description";

	EntityManagerFactory emf = JPAUtil.getEntityManagerFactory("db_config");
	GameRepository gameRepo = new GameRepository(emf, Game.class);

	private String success;
	private String fail;

	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getSuccess() {
		return success;
	}

	public String getFail() {
		return fail;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Game editGame(HttpServletRequest request) {

		String id = getValeurChamp(request, CHAMP_ID);
		String name = getValeurChamp(request, CHAMP_NAME);
		String description = getValeurChamp(request, CHAMP_DESCRIPTION);

		Game game = new Game();
		Integer idCurrentGame = validationId(id);
		if (idCurrentGame != null) {
			game = gameRepo.findById(idCurrentGame);

			try {
				validationName(name);
			} catch (Exception e) {
				setErreur(CHAMP_NAME, e.getMessage());
			}

			if (name.equals(gameRepo.getGameByName(name).getName()) && description.equals(gameRepo.getGameByDesc(description).getDescription())) {
				fail = "Ce jeux existe déjà !";
			} else {
				game.setName(name);
				try {
					validationDescription(description);
				} catch (Exception e) {
					setErreur(CHAMP_DESCRIPTION, e.getMessage());
				}
				game.setDescription(description);

				if (erreurs.isEmpty()) {
					gameRepo.update(game);
					success = "Le jeux a bien été modifié.";
				} else {
					fail = "Modification echouée.";
				}
			}

		}
		return game;
	}

	private void validationName(String name) throws Exception {
		if (name == null) {
			throw new Exception("Merci de saisir un nom de jeux.");
		}
	}

	private void validationDescription(String description) throws Exception {
		if (description != null) {
			if (description.length() < 10) {
				throw new Exception("La description doit contenir au moins 10 caractères.");
			}
		} else {
			throw new Exception("Merci de saisir une description.");
		}
	}

	private String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		}
		return valeur;
	}

	private void setErreur(String nomChamp, String message) {
		erreurs.put(nomChamp, message);
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
