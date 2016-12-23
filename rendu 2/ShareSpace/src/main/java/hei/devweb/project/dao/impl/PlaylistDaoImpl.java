package hei.devweb.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.devweb.project.dao.PlaylistDao;
import hei.devweb.project.entities.Film;
import hei.devweb.project.entities.Genre;
import hei.devweb.project.entities.Playlist;

public class PlaylistDaoImpl implements PlaylistDao{

	@Override
	public List<Playlist> listPlaylist() {
		// TODO Auto-generated method stub
		List<Playlist> playlists = new ArrayList<Playlist>();
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(Statement statement = (Statement) connection.createStatement()){
				try(ResultSet rsltSet = statement.executeQuery("SELECT * FROM playlist;")){
					while (rsltSet.next()){
						List<Film> f = new ArrayList<Film>();
						int id = rsltSet.getInt("idplaylist");
						try(PreparedStatement stmt = connection.prepareStatement("SELECT idmovie, title, starRating, rating, plot, "
								+ "releaseDate, dvdDate, runtime, director, studio, boxoffice, cover FROM movie LEFT JOIN playlist_movies "
								+ "ON movie.idmovie=playlist_movies.movieID where playlist_movies.playlistID = ?;")){
							stmt.setInt(1, id);
							try(ResultSet rSet = stmt.executeQuery()){
								while(rSet.next()){
									List<Genre> g = new ArrayList<Genre>();
									try(PreparedStatement statement1 = connection.prepareStatement("SELECT idgenre , value FROM genre "
											+ "LEFT JOIN genre_movies ON genre.idgenre=genre_movies.genreID where genre_movies.movieID = ?;")){
										statement1.setInt(1, id);
										try(ResultSet resultSet = statement1.executeQuery()){
											while(resultSet.next()){
												Genre genre = new Genre(resultSet.getInt("idgenre"),resultSet.getString("value"));
												g.add(genre);
											}
										}
										Film film = new Film(rSet.getString("title"), rSet.getDouble("starRating"), rSet.getString("rating"), 
												rSet.getString("plot"), rSet.getDate("releaseDate").toLocalDate(), 
												rSet.getDate("dvdDate").toLocalDate(), g , rSet.getInt("runtime"), rSet.getString("director"),
												rSet.getString("studio"), rSet.getInt("boxoffice"), rSet.getString("cover"));
										film.setId(rSet.getInt("idmovie"));
										f.add(film);
									}
								}
							}
							Playlist nplaylist =new Playlist(rsltSet.getString("playlistname"), rsltSet.getString("creator"), 
									rsltSet.getString("description"), rsltSet.getString("image"), f);
							nplaylist.setId(rsltSet.getInt("idplaylist"));
							playlists.add(nplaylist);
							
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO: handle finally clause
			e.printStackTrace();
		}
		return playlists;
	}

	@Override
	public Playlist getPlaylist(Integer id) {
		// TODO Auto-generated method stub
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM playlist WHERE idplaylist= ?;")){
				stmt.setInt(1, id);
				try(ResultSet rSet = stmt.executeQuery()){
					while (rSet.next()){
						List<Film> f = new ArrayList<Film>();
						try(PreparedStatement statement = connection.prepareStatement("SELECT idmovie , title FROM movie "
								+ "LEFT JOIN playlist_movies ON movie.idmovie=playlist_movies.movieID where playlist_movies.playlistID = ?;")){
							statement.setInt(1, id);
							try(ResultSet resultSet = statement.executeQuery()){
								while(resultSet.next()){
									List<Genre> g = new ArrayList<Genre>();
									try(PreparedStatement statement1 = connection.prepareStatement("SELECT idgenre , value FROM genre "
											+ "LEFT JOIN genre_movies ON genre.idgenre=genre_movies.genreID where genre_movies.movieID = ?;")){
										statement1.setInt(1, id);
										try(ResultSet resultSet1 = statement1.executeQuery()){
											while(resultSet1.next()){
												Genre genre = new Genre(resultSet1.getInt("idgenre"),resultSet1.getString("value"));
												g.add(genre);
											}
										}
										Film film = new Film(resultSet.getString("title"), resultSet.getDouble("starRating"), resultSet.getString("rating"), 
												resultSet.getString("plot"), resultSet.getDate("releaseDate").toLocalDate(), 
												resultSet.getDate("dvdDate").toLocalDate(), g , resultSet.getInt("runtime"), resultSet.getString("director"),
												resultSet.getString("studio"), resultSet.getInt("boxoffice"), resultSet.getString("cover"));
										film.setId(resultSet.getInt("idmovie"));
										f.add(film);
									}
								}
							}
						}
						Playlist playlist = new Playlist(rSet.getString("playlistname"), rSet.getString("creator"),
								rSet.getString("description"), rSet.getString("image"), f);
						return playlist;
					}
				}
			}
		}catch (SQLException e) {
			// TODO: handle finally clause
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Playlist addPlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO `playlist`( playlistname, creator, description, image) "
					+ "values (?,?,?,?);", com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS)){
				stmt.setString(1, playlist.getName());
				stmt.setString(2, playlist.getCreator());
				stmt.setString(3, playlist.getDescription());
				stmt.setString(4, playlist.getImage());
				stmt.executeUpdate();
				try(ResultSet rsltSet = stmt.getGeneratedKeys()){
					if(rsltSet.next()){
						List<Film> mList = playlist.getFilmList();
						playlist.getFilmNameList();
						playlist.setId((int) (rsltSet.getLong(1)));
						int playlistid = playlist.getId();
						for (int i=0; i<mList.size(); i++){
							try(PreparedStatement statement = connection.prepareStatement("INSERT INTO playlist_movies(playlistID, movieID)"
									+ " values (?,?);")){
								statement.setInt(1, playlistid);
								statement.setInt(2, mList.get(i).getId());
								statement.executeUpdate();
							}
						}
					}
					return playlist;
				}
			}
		} catch (SQLException e) {
			// TODO: handle finally clause
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Film> addFilmtoPlaylist(Film film, Playlist playlist) {
		// TODO Auto-generated method stub
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement stmt = connection.prepareStatement("INSERT INTO playlist_genre(playlistID, movieID) VALUES (?,?);")){
				stmt.setInt(1, film.getId());
				stmt.setInt(2, playlist.getId());
				stmt.executeUpdate();
				try(ResultSet rSet = stmt.getResultSet()){
					if (rSet.next()){
						List<Film> f = new ArrayList<Film>();
						try(PreparedStatement statement = connection.prepareStatement("SELECT idmovie , title FROM movie "
								+ "LEFT JOIN playlist_movies ON movie.idmovie=playlist_movies.movieID where playlist_movies.playlistID = ?;")){
							statement.setInt(1, playlist.getId());
							try(ResultSet resultSet = statement.executeQuery()){
								while(resultSet.next()){
									Film films = new Film();
									films.setId(resultSet.getInt("idmovie"));
									films.setTitle(resultSet.getString("title"));
									f.add(films);
								}
								return f;
							}
						}
					}
				}
			}
		}catch (SQLException e) {
			// TODO: handle finally clause
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deletePlaylist(Integer id) {
		// TODO Auto-generated method stub
		boolean wasdeleted = false;
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement stmt = connection.prepareStatement("DELETE * FROM playlist WHERE idplaylist= ?;")){
				stmt.setInt(1, id);
				try(ResultSet rSet = stmt.executeQuery()){
					System.out.println("Playlist "+id+" was succesfully deleted.");
					wasdeleted = true;
				}
			}
		}catch (SQLException e) {
			// TODO: handle finally clause
			e.printStackTrace();
		}
		return wasdeleted;
	}

}
