package hei.devweb.project.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.project.entities.Film;
import hei.devweb.project.entities.Genre;
import hei.devweb.project.services.FilmService;
import hei.devweb.project.services.GenreService;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/movie-list")
public class ListServlet extends AbstractGenericServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		TemplateEngine templateEngine = this.createTemplateEngine(request);

		WebContext context = new WebContext(request, response, request.getServletContext());
		
		List<Film> listfilms = FilmService.getInstance().listFilm();
		context.setVariable("films", listfilms);
		
		List<String> listgenre = new ArrayList<>();
		for (int i=0; i<listfilms.size(); i++){
			listgenre = listfilms.get(i).getNameGenre();
		}
		context.setVariable("listgenre", listgenre);
		
		List<Genre> lgenre = GenreService.getInstance().listGenre();
		context.setVariable("genres", lgenre);
			
		
		templateEngine.process("movie-list.html", context, response.getWriter());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
