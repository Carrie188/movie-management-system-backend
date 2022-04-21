package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Show;

public interface ShowRepository extends JpaRepository<Show, Long> {
	List<Show> findShowsByScreen(String screen);
}
