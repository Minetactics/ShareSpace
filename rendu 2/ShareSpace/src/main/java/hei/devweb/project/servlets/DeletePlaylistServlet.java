package hei.devweb.project.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hei.devweb.project.services.PlaylistService;

@WebServlet("/deleteplaylist")
public class DeletePlaylistServlet extends AbstractGenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2635514993787778456L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Integer id = Integer.parseInt(req.getParameter("id"));
		
		PlaylistService.getInstance().deletePlaylist(id);
		
		resp.sendRedirect("playlist");
	}
}
