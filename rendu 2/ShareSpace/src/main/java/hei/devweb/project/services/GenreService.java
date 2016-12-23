package hei.devweb.project.services;

import java.util.List;

import hei.devweb.project.dao.GenreDao;
import hei.devweb.project.dao.impl.GenreDaoImpl;
import hei.devweb.project.entities.Genre;

public class GenreService {

	private GenreDao genredao = new GenreDaoImpl();
	
	private static class GenreServiceHolder{
		private static GenreService instance = new GenreService();
	}
	
	public static GenreService getInstance(){
		return GenreServiceHolder.instance;
	}
	
	private GenreService(){
		
	}
	
	public List<Genre> listGenre(){
		return genredao.listGenre();
	}
	
	public Genre getGenre(Integer id){
		return genredao.getGenre(id);
	}
}
