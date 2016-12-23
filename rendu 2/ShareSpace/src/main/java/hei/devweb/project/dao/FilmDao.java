package hei.devweb.project.dao;

import java.util.List;

import hei.devweb.project.entities.Film;

public interface FilmDao {
	public List<Film> listFilm();
	
	public Film getFilm(Integer id);
	
	public Film addFilm(Film film);
	
	public boolean deleteFilm(Integer id);
}
