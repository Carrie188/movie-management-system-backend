package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class MovieController {

	@Autowired
	MovieRepository movieRepository;
	
	
	@GetMapping("/movies/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("id") long id) {
		Optional<Movie> movieData = movieRepository.findById(id);

		if (movieData.isPresent()) {
			return new ResponseEntity<>(movieData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	

	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(required = false) String title) {

		try {
			List<Movie> movies = new ArrayList<Movie>();
			if (title == null) {
//					courseRepository.findAll().forEach(courses::add);
				List<Movie> results = movieRepository.findAll();
				for (Movie m : results) {
					movies.add(m);
				}

			} else {
				movieRepository.findMovieByTitle(title).forEach(movies::add);
			}

			return new ResponseEntity<>(movies, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/movies")
	public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
		try {
			Movie newMovie = new Movie(movie.getTitle(), movie.getLanguage(), movie.getDuration(),movie.getReleaseDate(),movie.getOverview(),movie.getVoteScore()
					, movie.getBackgrounImagePath(), movie.getPosterPath());
			
			Movie _movie = movieRepository.save(newMovie);
			return new ResponseEntity<>(_movie, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/movies/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable("id") long id, @RequestBody Movie movie) {
		Optional<Movie> movieData = movieRepository.findById(id);

		if (movieData.isPresent()) {
			Movie _movie = movieData.get();
			_movie.setTitle(movie.getTitle());
			_movie.setLanguage(movie.getLanguage());
			_movie.setDuration(movie.getDuration());
			_movie.setReleaseDate(movie.getReleaseDate());
			_movie.setOverview(movie.getOverview());
			_movie.setVoteScore(movie.getVoteScore());
			_movie.setBackgrounImagePath(movie.getBackgrounImagePath());
			_movie.setPosterPath(movie.getPosterPath());
			
			return new ResponseEntity<>(movieRepository.save(_movie), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/movies/{id}")
	public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("id") long id) {
		try {
			movieRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
