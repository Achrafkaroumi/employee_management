package com.giantlink.grh;

import com.giantlink.grh.entities.ERole;
import com.giantlink.grh.entities.Role;
import com.giantlink.grh.repositories.RoleRepository;
import com.giantlink.grh.services.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giantlink.grh.entities.Company;
import com.giantlink.grh.services.CompanyService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

		roleRepository.deleteAll();
		roleRepository.save(new Role(ERole.ROLE_ADMIN));
		roleRepository.save(new Role(ERole.ROLE_USER));
		roleRepository.save(new Role(ERole.ROLE_MODERATOR));

	}

}
