package hei.devweb.project.dao;

import java.util.List;

import hei.devweb.project.entities.Film;
import hei.devweb.project.entities.Playlist;

public interface PlaylistDao {

	public List<Playlist> listPlaylist();
	
	public Playlist getPlaylist(Integer id);
	
	public Playlist addPlaylist(Playlist playlist);
	
	public List<Film> addFilmtoPlaylist(Film film, Playlist playlist);
	
	public boolean deletePlaylist(Integer id);
}
