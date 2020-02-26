package br.com.salao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.salao.entity.Authority;
import br.com.salao.entity.RegistrationForm;
import br.com.salao.entity.User;
import br.com.salao.repository.AuthorityRepository;
import br.com.salao.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	private UserRepository userRepo;
	private AuthorityRepository authRepo;
	private PasswordEncoder passwordEncoder;
	 
	@Autowired
	public RegistrationController(UserRepository userRepo, AuthorityRepository authRepo,
			PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.authRepo = authRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping
	public String registerForm() {
		return "registration";
	}
	
	@PostMapping
	public String processRegistration(RegistrationForm form) {
		Authority user_authority = authRepo.findByName("ROLE_USER");
		User user = form.toUser(passwordEncoder);	
		user.addAuthority(user_authority);
		userRepo.save(user);
		return "redirect:/login";
	}
	
}
