package hei.devweb.project.dao.impl;

import static org.assertj.core.api.Assertions.tuple;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import hei.devweb.project.dao.GenreDao;
import hei.devweb.project.entities.Genre;

public class GenreDaoTestCase {
	private GenreDao genreDao = new GenreDaoImpl();

	@Before
	public void initDb() throws Exception {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement stmt = connection.createStatement()) {
			stmt.executeUpdate("DELETE FROM genre");
			stmt.executeUpdate("INSERT INTO `genre`(idgenre, value) VALUES (1,'Comedy')");
			stmt.executeUpdate("INSERT INTO `genre`(idgenre, value) VALUES (2,'Classics')");
		}
	}

	@Test
	public void shouldListGenre() {
		// WHEN
		List<Genre> genres = genreDao.listGenre();
		// THEN
		Assertions.assertThat(genres).hasSize(2);
		Assertions.assertThat(genres).extracting("id", "name").containsOnly(
						tuple(1, "Comedy"), 
						tuple(2, "Classics"));
	}

	@Test
	public void ShouldGetGenre(){
		//WHEN
		Genre genre = genreDao.getGenre(1);
		//THEN
		Assertions.assertThat(genre).extracting("id","name").containsOnly(1,"Comedy");
	}
	
	@Test
	public void ShouldNotGetGenre(){

		//WHEN
		Genre genre = genreDao.getGenre(0);
		//THEN
		Assertions.assertThat(genre).isNull();
	}
}
