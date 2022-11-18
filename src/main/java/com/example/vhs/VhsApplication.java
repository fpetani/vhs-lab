package com.example.vhs;

import com.example.vhs.entity.Rental;
import com.example.vhs.entity.User;
import com.example.vhs.entity.VHS;
import com.example.vhs.repository.RentalRepository;
import com.example.vhs.repository.UserRepository;
import com.example.vhs.repository.VHSRepository;
import com.opencsv.bean.CsvToBeanBuilder;
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
import java.util.List;

@SpringBootApplication
public class VhsApplication implements CommandLineRunner {
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
		User testUser = new User();
		testUser.setUsername("test user");
		testUser.setPasswordHash(passwordEncoder.encode("password"));
		testUser.setRole("ROLE_ADMIN");
		userRepo.save(testUser);
		VHS vhsTest;
		vhsTest = repo.findById(Long.valueOf(1)).orElse(new VHS());
		vhsTest.setRented(true);
		Rental testRental = new Rental();
		testRental.setVhs(vhsTest);
		testRental.setId(Long.valueOf(1));
		testRental.setUser(testUser);
		rentalRepo.save(testRental);
	}
}
