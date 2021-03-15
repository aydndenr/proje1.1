package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table
public class turModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String filminturu;

	@OneToMany(mappedBy = "tur")
	private List<filmModel> filmlistesi;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilminturu() {
		return filminturu;
	}

	public void setFilminturu(String filminturu) {
		this.filminturu = filminturu;
	}

	public List<filmModel> getFilmlistesi() {
		return filmlistesi;
	}

	public void setFilmlistesi(List<filmModel> filmlistesi) {
		this.filmlistesi = filmlistesi;
	}

	
}
