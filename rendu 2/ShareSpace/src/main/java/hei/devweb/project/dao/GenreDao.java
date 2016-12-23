package hei.devweb.project.dao;

import java.util.List;

import hei.devweb.project.entities.Genre;

public interface GenreDao {

	public List<Genre> listGenre();
	
	public Genre getGenre(Integer id);
}
