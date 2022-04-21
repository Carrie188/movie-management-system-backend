package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Movie;
import com.example.demo.model.Show;
import com.example.demo.model.User;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ShowRepository;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class MovieReactSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieReactSystemApplication.class, args);
	}

	
	@Bean
 	ApplicationRunner init(UserRepository userRepository, MovieRepository movieRepository,ShowRepository showRepository) {
		return args ->{
			User[] users = {
		                new User("carriechen188@gmail.com","Carrie Chen","111111"),
		                new User("test@gmail.com","Test Test","123456"),
			};
			Movie[] movies = {
					new Movie("Batman", "en", 300, "10-2-2020", "overview", 9.8, "/imag1.jpg", "/imags.jpg"),
					new Movie("Batman 2", "en", 400, "10-2-2020", "overview", 9.8, "/imag2.jpg", "/imags.jpg"),
					new Movie("Batman 3", "en", 600, "10-2-2020", "overview", 9.8, "/imag3.jpg", "/imags.jpg"),
			};
			Show[] shows = {
					new Show("Screen A", "10-3-2020 19:00"),
					new Show("Screen B", "10-3-2020 19:00"),
					new Show("Screen C", "10-3-2020 19:00"),
					new Show("Screen A", "10-3-2020 12:00"),
					
			};
			movies[0].addShow(shows[0]);
			movies[0].addShow(shows[1]);
			movies[0].addShow(shows[2]);
			movies[0].addShow(shows[3]);
			users[0].setType("Admin");
			for (int i = 0; i < users.length; i++) {
				userRepository.save(users[i]);
			}
			for (int i = 0; i < movies.length; i++) {
				movieRepository.save(movies[i]);
			}
			for (int i = 0; i < shows.length; i++) {
				showRepository.save(shows[i]);
			}
			userRepository.findAll().forEach(System.out::println);
			movieRepository.findAll().forEach(System.out::println);
			showRepository.findAll().forEach(System.out::println);
	};
		
	}
}
