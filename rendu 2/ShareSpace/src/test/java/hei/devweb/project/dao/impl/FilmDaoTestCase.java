package hei.devweb.project.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import hei.devweb.project.dao.FilmDao;
import hei.devweb.project.dao.impl.DataSourceProvider;
import hei.devweb.project.dao.impl.FilmDaoImpl;
import hei.devweb.project.entities.Film;
import hei.devweb.project.entities.Genre;

public class FilmDaoTestCase {
	private FilmDao filmDao = new FilmDaoImpl();
	List<Genre> g1 = new ArrayList<Genre>();
	List<Genre> g2 = new ArrayList<Genre>();
	Genre genre1 = new Genre(1, "Comedy");
	Genre genre2 = new Genre(2, "Classics");


	@Before
	public void initDb() throws Exception {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement stmt = connection.createStatement()) {
			stmt.executeUpdate("DELETE FROM movie");
			stmt.executeUpdate("DELETE FROM genre");
			stmt.executeUpdate("DELETE FROM genre_movies");
			stmt.executeUpdate("INSERT INTO `genre`(idgenre, value) VALUES (1,'Comedy')");
			stmt.executeUpdate("INSERT INTO `genre`(idgenre, value) VALUES (2,'Classics')");
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
			stmt.executeUpdate("INSERT INTO `genre_movies`(id, genreID, movieID) VALUES (1,1,1)");
			stmt.executeUpdate("INSERT INTO `genre_movies`(id, genreID, movieID) VALUES (2,2,1)");
			stmt.executeUpdate("INSERT INTO `genre_movies`(id, genreID, movieID) VALUES (3,2,2)");
		}
	}

	@Test
	public void shouldListFilm() {
		// WHEN
		List<Film> films = filmDao.listFilm();
		g1.add(genre1);
		g1.add(genre2);
		g2.add(genre2);
		// THEN
		Assertions.assertThat(films).hasSize(2);
		Assertions.assertThat(films).extracting(
				"title", "plot", "starRating", "rating", "director", "releaseDate", "dvdDate", "boxoffice", "runtime",
				"studio", "cover").containsOnly(
						tuple("title 1", "plot of movie 1", 4.0, "PG", "director 1", LocalDate.of(2016, 11, 25), LocalDate.of(2016, 11, 25), 
								300000, 120, "studio 1", "image1.jpg"), 
						tuple("title 2", "plot of movie 2", 1.0, "G", "director 2", LocalDate.of(2016, 11, 01), LocalDate.of(2016, 11, 01), 
								100, 90, "studio 2", "image2.jpg"));
		Assertions.assertThat(films.get(0).getGenre().equals(g1));
		Assertions.assertThat(films.get(1).getGenre().equals(g2));

	}

	@Test
	public void shouldGetFilm() {
		//WHEN
		Film film = filmDao.getFilm(1);
		//THEN
		Assertions.assertThat(film.getTitle()).isEqualTo("title 1");
		Assertions.assertThat(film.getGenre().equals(g1));
		assertThat(film.getReleaseDate().equals(LocalDate.of(2016, 11, 25)));
	}

	@Test
	public void shouldNotGetFilm() {
		//WHEN
		Film film = filmDao.getFilm(0);
		//THEN
		Assertions.assertThat(film).isNull(); 
	}

	@Test
	public void shouldAddFilm() throws Exception {
		//GIVEN
		g1.add(genre1);
		g1.add(genre2);
		Film filmToAdd = new Film("title 1", 5.0 , "R", "plot of film 1", LocalDate.of(2010, 01, 01), LocalDate.of(2010, 02, 02), 
				g1, 200, "director 1", "studio 1", 400000, "cover 1");
		//WHEN
		Film filmAdded = filmDao.addFilm(filmToAdd);
		//THEN
		Assertions.assertThat(filmAdded).isNotNull();
		Assertions.assertThat(filmAdded.getId()).isNotNull();
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM movie WHERE idmovie= ?;")){
				stmt.setInt(1, filmAdded.getId());
				try(ResultSet rSet = stmt.executeQuery()){
					assertThat(rSet.next()).isTrue();
					assertThat(rSet.getInt("idmovie")).isEqualTo(filmAdded.getId());
				}
			}
		}
		catch (SQLException e) {
			// TODO: handle finally clause
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldDeleteFilm() throws Exception{
		//GIVEN
		//THEN
	}
}
