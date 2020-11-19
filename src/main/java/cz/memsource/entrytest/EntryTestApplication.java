package cz.memsource.entrytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EntryTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntryTestApplication.class, args);
	}
}
