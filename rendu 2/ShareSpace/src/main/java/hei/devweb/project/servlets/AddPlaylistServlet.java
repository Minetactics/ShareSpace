package hei.devweb.project.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hei.devweb.project.entities.Film;
import hei.devweb.project.entities.Playlist;
import hei.devweb.project.services.FilmService;
import hei.devweb.project.services.PlaylistService;

@WebServlet("/newplaylist")
public class AddPlaylistServlet extends AbstractGenericServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2939390226551206194L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		String name = req.getParameter("playlist-name");
		String creator = req.getParameter("playlist-creator");
		String description = req.getParameter("playlist-description");

		List<Film> lfilm = FilmService.getInstance().listFilm();
		List<Film> f = new ArrayList<>();
		for(int i=1; i<lfilm.size()+1; i++){
			String itt = String.valueOf(i);
			System.out.println("value of id"+itt);
			String id =req.getParameter(itt);
			System.out.println("is added (on/null)"+id);
			if(id !=null){
				f.add(FilmService.getInstance().getFilm(i));
				System.out.println("current filmlist"+f);
			}else{ System.out.println("film "+i+" is not selected");
			}
		}
		Playlist playlistToAdd = new Playlist(name, creator, description, "tbd", f);
		PlaylistService.getInstance().addPlaylist(playlistToAdd);
		resp.sendRedirect("playlist");
	}

}
