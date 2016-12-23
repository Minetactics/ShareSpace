package hei.devweb.project.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hei.devweb.project.entities.Comment;

@WebServlet("/movie-comment")
public class AddCommentServlet extends AbstractGenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1402638401242865455L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Integer rating =  Integer.parseInt(req.getParameter("rating"));
		String fname = req.getParameter("first-name");
		String lname = req.getParameter("last-name");
		String mail = req.getParameter("e-mail");
		String comment = req.getParameter("comment");
		
		Comment commentToAdd = new Comment(rating, fname, lname, mail, comment);
		System.out.println("voilà le commentaire)"+commentToAdd.toString());
		
		//ICI on fait semblant de lire et recevoir les commentaires des utilisateurs (mais en fait on s'en fout! shhhh)
		//On pourrait effectuer un stockage de tous les messages et ensuite les récupérer dans une fichier csv/xml pour utilisation commerciale
		resp.sendRedirect("comment-thanks.html");
	}
}
