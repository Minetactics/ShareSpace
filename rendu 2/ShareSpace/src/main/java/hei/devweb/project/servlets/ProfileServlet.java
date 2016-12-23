package hei.devweb.project.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends AbstractGenericServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> uAuth;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException{
		uAuth= new HashMap<>();
		uAuth.put("jojo", "pwdtrodur");
		uAuth.put("prof", "prof");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TemplateEngine templateEngine = this.createTemplateEngine(request);

		WebContext context = new WebContext(request, response, request.getServletContext());

		templateEngine.process("profile.html", context, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("identifiant");
		String mdp = request.getParameter("password");

		if(uAuth.containsKey(id) && uAuth.get(id).equals(mdp)){
			request.getSession().setAttribute("utilisateurCo", id);
			response.sendRedirect("profile");
		}
	}
}
