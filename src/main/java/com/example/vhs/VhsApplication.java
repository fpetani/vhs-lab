package com.example.vhs;

import com.example.vhs.entity.Rental;
import com.example.vhs.entity.User;
import com.example.vhs.entity.VHS;
import com.example.vhs.repository.RentalRepository;
import com.example.vhs.repository.UserRepository;
import com.example.vhs.repository.VHSRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class VhsApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(VhsApplication.class);
	@Autowired
	VHSRepository repo;
	@Autowired
	RentalRepository rentalRepo;
	@Autowired
	UserRepository userRepo;

	@Autowired
	PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(VhsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		List<VHS> vhs = new CsvToBeanBuilder<VHS>(new FileReader("src/movies.csv"))
				.withType(VHS.class).build().parse();
		repo.saveAll(vhs);
		fillUsers();
	}

	public void fillUsers(){
		List<User> users = new ArrayList<>();
		User testUser = new User();
		testUser.setUsername("Pero");
		testUser.setPasswordHash(passwordEncoder.encode("PeroSifra"));
		testUser.setRole("ROLE_USER");
		users.add(testUser);

		User testUser1 = new User();
		testUser1.setUsername("Ana");
		testUser1.setPasswordHash(passwordEncoder.encode("AnaSifra"));
		testUser1.setRole("ROLE_USER");
		users.add(testUser1);

		User testUser2 = new User();
		testUser2.setUsername("Direktor");
		testUser2.setPasswordHash(passwordEncoder.encode("DirektorSifra"));
		testUser2.setRole("ROLE_ADMIN");
		users.add(testUser2);

		User testUser3 = new User();
		testUser3.setUsername("Stipe");
		testUser3.setPasswordHash(passwordEncoder.encode("StipeSifra"));
		testUser3.setRole("ROLE_USER");
		users.add(testUser3);

		User testUser4 = new User();
		testUser4.setUsername("Jana");
		testUser4.setPasswordHash(passwordEncoder.encode("JanaSifra"));
		testUser4.setRole("ROLE_USER");
		users.add(testUser4);
		userRepo.saveAll(users);
	}
}
