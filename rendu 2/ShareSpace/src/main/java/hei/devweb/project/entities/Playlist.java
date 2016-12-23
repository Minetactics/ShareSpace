package hei.devweb.project.entities;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

	private String name;
	private Integer id;
	private String image;
	private String creator;
	private String description;
	private Integer size;
	private List<Film> filmList;
	public List<String> nameFilm;
	
	public Playlist(String name, String creator, String description, String image, List<Film> filmList){
		this.name=name;
		this.creator=creator;
		this.description=description;
		this.image=image;
		this.filmList = filmList;
		this.size= filmList.size();
	}

	public Playlist(){
		
	}
	
	public List<String> getFilmNameList(){
		List<String> nameFilmlist = new ArrayList<>();
		for (int i=0; i<filmList.size(); i++){
			nameFilmlist.add(filmList.get(i).getTitle());
		};
		this.nameFilm = nameFilmlist;
		return nameFilm;
	}
	
	public String getName() {
		return name;
	}
	public Integer getId() {
		return id;
	}
	public String getImage() {
		return image;
	}
	public String getCreator() {
		return creator;
	}
	public String getDescription() {
		return description;
	}
	public Integer getSize() {
		return size;
	}
	public List<Film> getFilmList() {
		return filmList;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public void setFilmList(List<Film> filmList) {
		this.filmList = filmList;
	}
	
}
