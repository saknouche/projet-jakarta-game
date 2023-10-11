package fr.sadev.app.forms;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

import fr.sadev.app.beans.User;
import fr.sadev.app.repository.UserRepository;
import fr.sadev.app.utils.Hash;
import fr.sadev.app.utils.JPAUtil;

public class RegisterForm {

	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASSWORD = "password";

	EntityManagerFactory emf = JPAUtil.getEntityManagerFactory("db_config");
	UserRepository userRepo = new UserRepository(emf, User.class);
	
	private String success;
	private String faillure;


	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getSuccess() {
		return success;
	}

	public String getFaillure() {
		return faillure;
	}


	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public User registerUser(HttpServletRequest request) {

		String email = getValeurChamp(request, CHAMP_EMAIL);
		String password = getValeurChamp(request, CHAMP_PASSWORD);
		
		User user = new User();
		
		try {
			validationEmail(email);
		} catch (Exception e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		user.setEmail(email);
		
		try {
			validationMotDePasse(password);
		} catch (Exception e) {
			setErreur(CHAMP_PASSWORD, e.getMessage());
		}
		String passwordHashed = Hash.encrypt(password);
		user.setPassword(passwordHashed);
		if(erreurs.isEmpty()) {	
			if(userRepo.getUserByEmail(email) != null && email.equals(userRepo.getUserByEmail(email).getEmail())) {
				faillure = "Cet utilisateur existe déjà !";
			}else {				
				userRepo.create(user);
				success = "L'inscription a été effectuée avec succès";
			}
		}else {
			faillure = "Inscription échouée";
		}
		
		return user;
	}
	

	private void validationEmail(String email) throws Exception{
		if(email != null) {
			if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {				
				throw new Exception("Merci de saisir une adresse email valide.");
			}
		}else {
			throw new Exception("Merci de saisir une adresse email.");
		}
	}
	
	private void validationMotDePasse(String motDePasse) throws Exception{
		if(motDePasse != null) {
			if(motDePasse.length() < 3) {
				throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
			}
		}else {
			throw new Exception("Merci de saisir un mot de passe.");
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
}
