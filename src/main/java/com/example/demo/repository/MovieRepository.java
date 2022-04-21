package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	List<Movie> findMovieByTitle(String title);
}
