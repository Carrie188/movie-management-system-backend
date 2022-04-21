package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;


	@Column(name = "language")
	private String language;
	
	@Column(name = "duration")
	private int duration;
	


	@Column(name = "releaseDate")
	private String releaseDate;
	
	@Column(name = "overview")
	private String overview;
	
	@Column(name = "voteScore")
	private double voteScore;
	
	@Column(name = "backgrounPath")
	private String backgrounImagePath;
	
	@Column(name = "posterPath")
	private String posterPath;
	
	public Movie() {
		
	}

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Show> shows = new HashSet<>();
	



	public Movie(String title, String language,int duration, String releaseDate, String overview, double voteScore,
			String backgrounImagePath, String posterPath) {
		super();
		this.title = title;
		this.language = language;
		this.releaseDate = releaseDate;
		this.overview = overview;
		this.voteScore = voteScore;
		this.backgrounImagePath = backgrounImagePath;
		this.posterPath = posterPath;
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getBackgrounImagePath() {
		return backgrounImagePath;
	}

	public void setBackgrounImagePath(String backgrounImagePath) {
		this.backgrounImagePath = backgrounImagePath;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public void setVoteScore(double voteScore) {
		this.voteScore = voteScore;
	}
	public double getVoteScore() {
		return voteScore;
	}
	
	public Set<Show> getShows() {
		return shows;
	}

	public void setShows(Set<Show> shows) {
		this.shows = shows;
	}
	
	public void addShow(Show show) {
		this.shows.add(show);
		show.setMovie(this);
	}

}
