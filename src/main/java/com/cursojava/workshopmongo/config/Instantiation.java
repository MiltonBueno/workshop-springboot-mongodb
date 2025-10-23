package com.cursojava.workshopmongo.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.cursojava.workshopmongo.domain.Post;
import com.cursojava.workshopmongo.domain.User;
import com.cursojava.workshopmongo.dto.AuthorDTO;
import com.cursojava.workshopmongo.repositories.PostRepository;
import com.cursojava.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                .withZone(ZoneId.of("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		// Converter string -> LocalDate -> Instant
		Instant date1 = LocalDate.parse("21/03/2018", formatter)
		                         .atStartOfDay(ZoneId.of("GMT"))
		                         .toInstant();

		Instant date2 = LocalDate.parse("23/03/2018", formatter)
		                         .atStartOfDay(ZoneId.of("GMT"))
		                         .toInstant();

		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, date1, "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, date2, "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}