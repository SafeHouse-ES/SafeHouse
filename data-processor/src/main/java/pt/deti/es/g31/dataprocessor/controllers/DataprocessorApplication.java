package pt.deti.es.g31.dataprocessor.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"pt.deti.es.g31.dataprocessor"})
public class DataprocessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataprocessorApplication.class, args);
	}

}
