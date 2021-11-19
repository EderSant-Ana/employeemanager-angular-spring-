package com.santana.employeemanager;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.santana.employeemanager.model.Employee;
import com.santana.employeemanager.repo.EmployeeRepo;
import com.santana.employeemanager.service.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeemanagerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmployeemanagerApplication.class, args);
	}

	private EmployeeService employeeService;
	private EmployeeRepo employeeRepo;

	@Override
	public void run(String... args) throws Exception {

		Employee e1 = Employee.builder().name("Daniel Samuels").email("daniel@gmail.com").jobTitle("JavaScript")
				.phone("4570090331").imageUrl("https://bootdey.com/img/Content/avatar/avatar1.png").build();
		Employee e2 = Employee.builder().name("Christine Jones").email("christine@gmail.com").jobTitle("UX/UI")
				.phone("4570090332").imageUrl("https://bootdey.com/img/Content/avatar/avatar3.png").build();
		Employee e3 = Employee.builder().name("Dave Rogers").email("dave@gmail.com").jobTitle("Java")
				.phone("4570090333").imageUrl("https://bootdey.com/img/Content/avatar/avatar7.png").build();
		Employee e4 = Employee.builder().name("Andy Maxwell").email("andy@gmail.com").jobTitle("SQL")
				.phone("4570090334").imageUrl("https://bootdey.com/img/Content/avatar/avatar2.png").build();

		employeeService.createEmployee(e1);
		employeeService.createEmployee(e2);
		employeeService.createEmployee(e3);
		employeeService.createEmployee(e4);

		employeeRepo.findAll().forEach(e -> log.info(e.toString()));
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
