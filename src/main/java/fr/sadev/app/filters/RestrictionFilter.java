package fr.sadev.app.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/home", "/insert", "/deconnect", "/edit", "/delete", "/show" })
public class RestrictionFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	public static final String ATT_SESSION_USER = "user";
	public static final String ACCES_CONNEXION = "/connect";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/* Cast des objets request et response */
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		/* Récupération de la session depuis la requête */
		HttpSession session = req.getSession();

		/* Non-filtrage des ressources statiques */
//		String chemin = req.getRequestURI().substring(req.getContextPath().length());
//		if (chemin.startsWith("/assets")) {
//			chain.doFilter(request, response);
//			return;
//		}
		/**
		 * Si l'objet utilisateur n'existe pas dans la session en cours, alors
		 * l'utilisateur n'est pas connecté.
		 */

		if (session.getAttribute(ATT_SESSION_USER) != null) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect(req.getContextPath() + ACCES_CONNEXION);
		}

	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

}
