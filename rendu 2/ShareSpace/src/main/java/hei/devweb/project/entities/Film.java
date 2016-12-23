package hei.devweb.project.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Film {

	private Integer id;
	private String title;
	private double starRating;
	private String plot;
	private String rating;
	private LocalDate releaseDate;
	private LocalDate dvdDate;
	private List<Genre> genre;
	private Integer runtime;
	private String director;
	private String studio;
	private Integer boxoffice;
	private String cover;
	private List<String> nameGenre;

	// ATTENTION! L'ENUM RATING N'EST PAS GERE ICI!! (il est déclaré simplement)
	public Film(String title, double starRating, String ratename, String plot, LocalDate relDate, LocalDate dvdDate, 
			List<Genre> genre, Integer runtime, String director, String studio, Integer boxoffice, String cover) {
		this.title= title;
		this.starRating = starRating;
		this.plot = plot;
		this.releaseDate = relDate;
		this.dvdDate = dvdDate;
		this.genre = genre;
		this.runtime = runtime;
		this.director = director;
		this.studio= studio;
		this.boxoffice = boxoffice;
		this.cover = cover;
		this.rating=ratename;
	}
	
	public Film(){
	}

	public List<String> getNameGenre(){
		List<String> nameGenrelist = new ArrayList<>();
		for (int i=0; i<genre.size(); i++){
			nameGenrelist.add(genre.get(i).toString());
		}
		this.nameGenre=nameGenrelist;
		return nameGenre;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public void setStarRating(double starRating) {
		this.starRating = starRating;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	public void setDvdDate(LocalDate dvdDate) {
		this.dvdDate = dvdDate;
	}
	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}
	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public void setStudio(String studio) {
		this.studio = studio;
	}
	public void setBoxoffice(Integer boxoffice) {
		this.boxoffice = boxoffice;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	public Integer getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public double getStarRating() {
		return starRating;
	}
	public String getPlot() {
		return plot;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public LocalDate getDvdDate() {
		return dvdDate;
	}
	public List<Genre> getGenre() {
		return genre;
	}
	public Integer getRuntime() {
		return runtime;
	}
	public String getDirector() {
		return director;
	}
	public String getStudio() {
		return studio;
	}
	public Integer getBoxoffice() {
		return boxoffice;
	}
	public String getCover() {
		return cover;
	}

}
