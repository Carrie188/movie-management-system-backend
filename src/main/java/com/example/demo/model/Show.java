package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shows")
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "startTime")
	private String startTime;
	
	@Column(name = "screen")
	private String screen;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "movie_id", nullable = true)
	@JsonIgnore
	private Movie movie;

	public Show() {
		
	}
	
	public Show( String screen, String startTime) {
		super();
		this.startTime = startTime;
		this.screen = screen;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
		
	}
	
	
	
	
}
