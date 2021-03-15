package com.example.demo.models;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


//  Medya, Dil seçenekleri 
@Entity
@Table
public class filmModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String media;
	
	@OneToMany(mappedBy = "filmler")
	private List<oyuncuModel> oyuncular;
	


	public List<oyuncuModel> getOyuncular() {
		return oyuncular;
	}

	public void setOyuncular(List<oyuncuModel> oyuncular) {
		this.oyuncular = oyuncular;
	}

	
	@Column(nullable = false)
	private int year;
	
	@Column
	private String content;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn
	private turModel tur;

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

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public turModel getTur() {
		return tur;
	}

	public void setTur(turModel tur) {
		this.tur = tur;
	}
	
}
