package com.example.demo.models;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class oyuncuModel {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn
    private filmModel filmler;



	




	public filmModel getFilmler() {
		return filmler;
	}




	public void setFilmler(filmModel filmler) {
		this.filmler = filmler;
	}




	@Column
	private String content;




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getSurname() {
		return surname;
	}




	public void setSurname(String surname) {
		this.surname = surname;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
