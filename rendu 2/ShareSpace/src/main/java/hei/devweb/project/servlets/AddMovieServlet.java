package hei.devweb.project.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hei.devweb.project.entities.Film;
import hei.devweb.project.entities.Genre;
import hei.devweb.project.services.FilmService;
import hei.devweb.project.services.GenreService;

@WebServlet("/addmovie")
public class AddMovieServlet extends AbstractGenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2710613802603097078L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String title = req.getParameter("new-movie-name");
		String plot = req.getParameter("new-movie-plot");
		String director = req.getParameter("new-movie-dir");
		String studio = req.getParameter("new-movie-studio");
		String ratename = req.getParameter("new-rating");
		String cover = "to be defined";
		LocalDate reldate = LocalDate.parse(req.getParameter("new-movie-reldate"), formatter);
		LocalDate dvddate = LocalDate.parse(req.getParameter("new-movie-dvddate"), formatter);
		Integer boxoffice = Integer.parseInt(req.getParameter("new-movie-bo"));
		Integer runtime = Integer.parseInt(req.getParameter("new-movie-run"));
		Double starRate = Double.parseDouble(req.getParameter("new-movie-starrate"));

		List<Genre> lgenre = GenreService.getInstance().listGenre();
		List<Genre> g = new ArrayList<>();
		for(int i=1; i<lgenre.size()+1; i++){
			String itt = String.valueOf(i);
			System.out.println(itt);
			String id =req.getParameter(itt);
			System.out.println(id);
			if(id !=null){
				g.add(GenreService.getInstance().getGenre(i));
				System.out.println(g);
			}else{ System.out.println("genre "+i+"is not selected");
			}
		}
		Film filmToAdd = new Film(title, starRate, ratename, plot, reldate, dvddate, g, runtime, director, studio, boxoffice, cover);
		FilmService.getInstance().addFilm(filmToAdd);
		resp.sendRedirect("movie-list");
	}

}
