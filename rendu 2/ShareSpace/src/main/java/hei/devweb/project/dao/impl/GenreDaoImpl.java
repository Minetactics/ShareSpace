package hei.devweb.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hei.devweb.project.dao.GenreDao;
import hei.devweb.project.entities.Genre;

public class GenreDaoImpl implements GenreDao {

	@Override
	public List<Genre> listGenre() {
		// TODO Auto-generated method stub
		List<Genre> genres = new ArrayList<Genre>();
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(Statement statement = (Statement) connection.createStatement()){
				try(ResultSet rsltSet = statement.executeQuery("SELECT * FROM genre;")){
					while (rsltSet.next()){
						genres.add(new Genre(rsltSet.getInt("idgenre"), rsltSet.getString("value")));
					}
				}
			}
		} catch (SQLException e) {
			// TODO: handle finally clause
			e.printStackTrace();
		}
		return genres;
	}

	@Override
	public Genre getGenre(Integer id) {
		// TODO Auto-generated method stub
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM genre WHERE idgenre= ?;")){
				stmt.setInt(1, id);
				try(ResultSet rSet = stmt.executeQuery()){
					while (rSet.next()){
						Genre genre = new Genre(rSet.getInt("idgenre"),rSet.getString("value"));
						return genre;
					}
				}
			}
		} catch (SQLException e) {
			// TODO: handle finally clause
			e.printStackTrace();
		}
		return null;
	}

}
