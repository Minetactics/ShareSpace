package hei.devweb.project.services;

import java.util.List;

import hei.devweb.project.dao.PlaylistDao;
import hei.devweb.project.dao.impl.PlaylistDaoImpl;
import hei.devweb.project.entities.Film;
import hei.devweb.project.entities.Playlist;

public class PlaylistService {
private PlaylistDao playlistDao = new PlaylistDaoImpl(); 
	
	private static class PlaylistServiceHolder{
		private static PlaylistService instance = new PlaylistService();
	}
	
	public static PlaylistService getInstance(){
		return PlaylistServiceHolder.instance;
	}
	
	private PlaylistService(){
	}
	
	public List<Playlist> listPlaylist(){
		return playlistDao.listPlaylist();
	}
	
	public Playlist getPlaylist(Integer id){
		return playlistDao.getPlaylist(id);
	}
	
	public Playlist addPlaylist(Playlist playlist){
		return playlistDao.addPlaylist(playlist);
	}
	
	public boolean deletePlaylist(Integer id){
		return playlistDao.deletePlaylist(id);
	}
	
	public List<Film> addFilmtoPlaylist(Film film, Playlist playlist){
		return playlistDao.addFilmtoPlaylist(film, playlist);
	}
}
