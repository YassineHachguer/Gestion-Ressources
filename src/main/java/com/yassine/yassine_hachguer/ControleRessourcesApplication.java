package com.yassine.yassine_hachguer;

import com.yassine.yassine_hachguer.entities.Ressource;
import com.yassine.yassine_hachguer.repository.RessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class ControleRessourcesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ControleRessourcesApplication.class, args);
	}
    @Autowired
	private RessourceRepository ressourceRepository;
	@Override
	public void run(String... args) throws Exception {
		Ressource ressource1 = Ressource.builder()
				.titre("ressource 1")
				.dateAchat(new Date())
				.prix(100)
				.note(5)
				.build();
		Ressource ressource2 = Ressource.builder()
				.titre("ressource 2")
				.dateAchat(new Date())
				.prix(200)
				.note(4)
				.build();

        ressourceRepository.save(ressource1);
		ressourceRepository.save(ressource2);



	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}
}
