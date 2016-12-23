package hei.devweb.project.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import hei.devweb.project.dao.PlaylistDao;
import hei.devweb.project.dao.impl.PlaylistDaoImpl;
import hei.devweb.project.entities.Film;
import hei.devweb.project.entities.Playlist;

public class PlaylistDaoTestCase {

	private PlaylistDao playlistDao = new PlaylistDaoImpl();
	List<Film> f1 = new ArrayList<Film>();
	List<Film> f2 = new ArrayList<Film>();
	Film film1 = new Film();
	Film film2 = new Film();

	@Before
	public void initDb() throws Exception {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement stmt = connection.createStatement()) {
			stmt.executeUpdate("DELETE FROM movie");
			stmt.executeUpdate("DELETE FROM playlist");
			stmt.executeUpdate("DELETE FROM playlist_movies");
			stmt.executeUpdate("INSERT INTO `playlist`(idplaylist, playlistname, creator, description, image) "
					+ "VALUES (1, 'playlist 1', 'creator 1', 'desc 1', 'img1.jpg')");
			stmt.executeUpdate("INSERT INTO `playlist`(idplaylist, playlistname, creator, description, image) "
					+ "VALUES (2, 'playlist 2', 'creator 2', 'desc 2', 'img2.jpg')");
			stmt.executeUpdate(
					"INSERT INTO `movie`(idmovie, title, plot, starRating, rating, director, releaseDate, dvdDate,"
							+ "boxoffice, runtime, studio, cover) "
							+ "VALUES (1, 'title 1', 'plot of movie 1', 4.0, 'PG', 'director 1', '2016-11-25', '2016-11-25',"
							+ "300000, 120, 'studio 1', 'image1.jpg')");
			stmt.executeUpdate(
					"INSERT INTO `movie`(idmovie, title, plot, starRating, rating, director, releaseDate, dvdDate,"
							+ "boxoffice, runtime, studio, cover) "
							+ "VALUES (2, 'title 2', 'plot of movie 2', 1.0, 'G', 'director 2', '2016-11-01', '2016-11-01',"
							+ "100, 90, 'studio 2', 'image2.jpg')");
			stmt.executeUpdate("INSERT INTO `playlist_movies`(id, playlistID, movieID) VALUES (1,1,1)");
			stmt.executeUpdate("INSERT INTO `playlist_movies`(id, playlistID, movieID) VALUES (2,1,2)");
			stmt.executeUpdate("INSERT INTO `playlist_movies`(id, playlistID, movieID) VALUES (3,2,2)");
		}
	}

	@Test
	public void shouldListPlaylist() {
		// WHEN
		List<Playlist> playlists= playlistDao.listPlaylist();
		f1.add(film1);
		f1.add(film2);
		f2.add(film2);
		// THEN
		Assertions.assertThat(playlists).hasSize(2);
		Assertions.assertThat(playlists).extracting(
				"name", "creator", "description", "image").containsOnly(
						tuple("playlist 1", "creator 1", "desc 1", "img1.jpg"), 
						tuple("playlist 2", "creator 2", "desc 2", "img2.jpg"));
		Assertions.assertThat(playlists.get(0).getFilmList().equals(f1));
		Assertions.assertThat(playlists.get(1).getFilmList().equals(f2));

	}

	@Test
	public void shouldGetPlaylist() {
		//WHEN
		Playlist playlist = playlistDao.getPlaylist(1);
		//THEN
		Assertions.assertThat(playlist.getName()).isEqualTo("playlist 1");
		Assertions.assertThat(playlist.getFilmList().equals(f1));
		Assertions.assertThat(playlist.getImage()).isEqualTo("img1.jpg");
	}

	@Test
	public void shouldNotGetPlaylist() {
		//WHEN
		Playlist playlist = playlistDao.getPlaylist(0);
		//THEN
		Assertions.assertThat(playlist).isNull(); 
	}

	@Test
	public void shouldAddPlaylist() throws Exception {
		//GIVEN
		film1.setId(1);
		film2.setId(2);
		f1.add(film1);
		f1.add(film2);
		Playlist playlistToAdd = new Playlist("name 1", "creator 1", "description 1", "image 1", f1);
		//WHEN
		Playlist playlistAdded = playlistDao.addPlaylist(playlistToAdd);
		//THEN
		Assertions.assertThat(playlistAdded).isNotNull();
		Assertions.assertThat(playlistAdded.getId()).isNotNull();
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM playlist WHERE idplaylist= ?;")){
				stmt.setInt(1, playlistAdded.getId());
				try(ResultSet rSet = stmt.executeQuery()){
					assertThat(rSet.next()).isTrue();
					assertThat(rSet.getInt("idplaylist")).isEqualTo(playlistAdded.getId());
				}
			}
		}
		catch (SQLException e) {
			// TODO: handle finally clause
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldDeletePlaylist() throws Exception{
		//GIVEN
		//THEN
	}
}
