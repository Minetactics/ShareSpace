package hei.devweb.project.services;

import java.util.List;

import hei.devweb.project.dao.FilmDao;
import hei.devweb.project.dao.impl.FilmDaoImpl;
import hei.devweb.project.entities.Film;

public class FilmService {

	private FilmDao filmDao = new FilmDaoImpl(); 
	
	private static class FilmServiceHolder{
		private static FilmService instance = new FilmService();
	}
	
	public static FilmService getInstance(){
		return FilmServiceHolder.instance;
	}
	
	private FilmService(){
	}
	
	public List<Film> listFilm(){
		return filmDao.listFilm();
	}
	
	public Film getFilm(Integer id){
		return filmDao.getFilm(id);
	}
	
	public Film addFilm(Film film){
		return filmDao.addFilm(film);
	}
	
	public boolean deleteFilm(Integer id){
		return filmDao.deleteFilm(id);
	}
}
