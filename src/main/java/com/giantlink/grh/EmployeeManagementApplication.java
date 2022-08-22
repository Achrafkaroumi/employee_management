package com.giantlink.grh;
import com.giantlink.grh.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giantlink.grh.services.CompanyService;

@SpringBootApplication
public class EmployeeManagementApplication implements CommandLineRunner {

	@Autowired
	CompanyService companyService;
	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello employee management");

//		roleRepository.deleteAll();
//		roleRepository.save(new Role(ERole.ROLE_ADMIN));
//		roleRepository.save(new Role(ERole.ROLE_USER));
//		roleRepository.save(new Role(ERole.ROLE_MODERATOR));

	}

}
