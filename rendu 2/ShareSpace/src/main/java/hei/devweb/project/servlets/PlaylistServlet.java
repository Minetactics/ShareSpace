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
import hei.devweb.project.entities.Playlist;
import hei.devweb.project.services.FilmService;
import hei.devweb.project.services.PlaylistService;

/**
 * Servlet implementation class PlaylistServlet
 */
@WebServlet("/playlist")
public class PlaylistServlet extends AbstractGenericServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaylistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(request);

		WebContext context = new WebContext(request, response, request.getServletContext());
		
		List<Playlist> listplaylist = PlaylistService.getInstance().listPlaylist();
		context.setVariable("playlists", listplaylist);
		
		List<Film> listfilms = FilmService.getInstance().listFilm();
		context.setVariable("films", listfilms);
		
		List<String> listfilm = new ArrayList<>();
			for (int i=0; i<listplaylist.size(); i++){
				listfilm = listplaylist.get(i).getFilmNameList();
			}
		context.setVariable("listfilm", listfilm);
		templateEngine.process("playlist.html", context, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
