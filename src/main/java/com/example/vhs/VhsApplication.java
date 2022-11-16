package com.example.vhs;

import com.example.vhs.entity.Rental;
import com.example.vhs.entity.VHS;
import com.example.vhs.repository.VHSRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootApplication
public class VhsApplication implements CommandLineRunner {
	@Autowired
	VHSRepository repo;


	public static void main(String[] args) {
		SpringApplication.run(VhsApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception{
		List<VHS> vhs = new CsvToBeanBuilder<VHS>(new FileReader("src/movies.csv"))
				.withType(VHS.class).build().parse();

		repo.saveAll(vhs);

        /*Rental testRental;

        LocalDate from = LocalDate.now();
        LocalDate to = from.plusDays(10);

        long result = ChronoUnit.DAYS.between(to, from);
        System.out.println(result);*/

	}
}
