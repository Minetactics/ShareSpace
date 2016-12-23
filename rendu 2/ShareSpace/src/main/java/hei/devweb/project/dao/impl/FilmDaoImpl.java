package hei.devweb.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.devweb.project.dao.impl.DataSourceProvider;
import hei.devweb.project.dao.FilmDao;
import hei.devweb.project.entities.Film;
import hei.devweb.project.entities.Genre;

public class FilmDaoImpl implements FilmDao{

	public List<Film> listFilm() {
		List<Film> films = new ArrayList<Film>();
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(Statement statement = (Statement) connection.createStatement()){
				try(ResultSet rsltSet = statement.executeQuery("SELECT * FROM movie;")){
					while (rsltSet.next()){
						List<Genre> g = new ArrayList<Genre>();
						int id = rsltSet.getInt("idmovie");
						try(PreparedStatement stmt = connection.prepareStatement("SELECT idgenre , value FROM genre "
								+ "LEFT JOIN genre_movies ON genre.idgenre=genre_movies.genreID where genre_movies.movieID = ?;")){
							stmt.setInt(1, id);
							try(ResultSet rSet = stmt.executeQuery()){
								while(rSet.next()){
									Genre genre = new Genre(rSet.getInt("idgenre"),rSet.getString("value"));
									g.add(genre);
								}
							}
						}
						Film nfilm = new Film(rsltSet.getString("title"), rsltSet.getDouble("starRating"), rsltSet.getString("rating"), 
								rsltSet.getString("plot"), rsltSet.getDate("releaseDate").toLocalDate(), 
								rsltSet.getDate("dvdDate").toLocalDate(), g , rsltSet.getInt("runtime"), rsltSet.getString("director"),
								rsltSet.getString("studio"), rsltSet.getInt("boxoffice"), rsltSet.getString("cover"));
						nfilm.setId(rsltSet.getInt("idmovie"));
						films.add(nfilm);
					}
				}
			}
		} catch (SQLException e) {
			// TODO: handle finally clause
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public Film getFilm(Integer id) {
		// TODO Auto-generated method stub
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM movie WHERE idmovie= ?;")){
				stmt.setInt(1, id);
				try(ResultSet rSet = stmt.executeQuery()){
					while (rSet.next()){
						List<Genre> g = new ArrayList<Genre>();
						try(PreparedStatement statement = connection.prepareStatement("SELECT idgenre , value FROM genre "
								+ "LEFT JOIN genre_movies ON genre.idgenre=genre_movies.genreID where genre_movies.movieID = ?;")){
							statement.setInt(1, id);
							try(ResultSet resultSet = statement.executeQuery()){
								while(resultSet.next()){
									Genre genre = new Genre(resultSet.getInt("idgenre"),resultSet.getString("value"));
									g.add(genre);
								}
							}
						}

						Film film = new Film(rSet.getString("title"), rSet.getDouble("starRating"), rSet.getString("rating"), 
								rSet.getString("plot"), rSet.getDate("releaseDate").toLocalDate(), 
								rSet.getDate("dvdDate").toLocalDate(), g , rSet.getInt("runtime"), rSet.getString("director"),
								rSet.getString("studio"), rSet.getInt("boxoffice"), rSet.getString("cover"));
						film.setId(rSet.getInt("idmovie"));
						return film;
					}
				}
			}
		}catch (SQLException e) {
			// TODO: handle finally clause
			e.printStackTrace();
		}
		return null;
	}

	public Film addFilm(Film film) {
		// TODO Auto-generated method stub
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO `movie`(title, starRating, rating, plot, releaseDate,"
					+ "dvdDate, runtime, director, studio, boxoffice, cover) values (?,?,?,?,?,?,?,?,?,?,?);", 
					com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS)){
				stmt.setString(1, film.getTitle());
				stmt.setDouble(2, film.getStarRating());
				stmt.setString(3,film.getRating());
				stmt.setString(4, film.getPlot());
				stmt.setDate(5,java.sql.Date.valueOf(film.getReleaseDate()));
				stmt.setDate(6, java.sql.Date.valueOf(film.getDvdDate()));
				stmt.setInt(7, film.getRuntime());
				stmt.setString(8, film.getDirector());
				stmt.setString(9, film.getStudio());
				stmt.setInt(10, film.getBoxoffice());
				stmt.setString(11, film.getCover());
				stmt.executeUpdate();
				try(ResultSet rsltSet = stmt.getGeneratedKeys()){
					if(rsltSet.next()){
						film.setId((int) (rsltSet.getLong(1)));
						int filmid = film.getId();
						List<Genre> gList = film.getGenre();
						for (int i=0; i<gList.size(); i++){
							try(PreparedStatement statement = connection.prepareStatement("INSERT INTO genre_movies(genreID, movieID)"
									+ " values (?,?);")){
								statement.setInt(1, gList.get(i).getId());
								statement.setInt(2, filmid);
								statement.executeUpdate();
							}
						}
					}
					return film;
				}
			}
		} catch (SQLException e) {
			// TODO: handle finally clause
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public boolean deleteFilm(Integer id) {
		// TODO Auto-generated method stub
		boolean wasdeleted = false;
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement stmt = connection.prepareStatement("DELETE * FROM movie WHERE idmovie= ?;")){
				stmt.setInt(1, id);
				try(ResultSet rSet = stmt.executeQuery()){
					System.out.println("Movie "+id+" was successfully deleted.");
					wasdeleted = true;
					return wasdeleted;
				}
			}
		}catch (SQLException e) {
			// TODO: handle finally clause
			e.printStackTrace();
		}
		return wasdeleted;
	}
}